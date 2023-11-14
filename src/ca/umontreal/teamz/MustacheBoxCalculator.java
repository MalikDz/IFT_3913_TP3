package ca.umontreal.teamz;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.IOException;
import ca.umontreal.teamz.data.TestClassMetricsData;
import ca.umontreal.teamz.utilities.FileManipulation;

/**
 *
 * @author Malik Abada (matricule 20173066)
 * @author Christian Lungescu (matricule 20079725)
 * 
 */

public class MustacheBoxCalculator {

	private static final double LIMIT_CONST = 1.5;
	private static final String WMC_METRIC_NAME = "Wmc";
	private static final String TLOC_METRIC_NAME = "Tloc";
	private static final String TASSERT_METRIC_NAME = "Tassert";
	private static final String METRICS_DATA_FILE_NAME = "jfreechart-test-stats.csv";
	private static final Comparator<TestClassMetricsData> TLOC_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return Double.compare(o1.getTloc(), o2.getTloc());
		}
	};
	private static final Comparator<TestClassMetricsData> WMC_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return Double.compare(o1.getWmc(), o2.getWmc());
		}
	};
	private static final Comparator<TestClassMetricsData> TASSERT_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return Double.compare(o1.getTassert(), o2.getTassert());
		}
	};

	public static void main(String[] args) throws IOException {
		List<TestClassMetricsData> testClassesMetricsData = parseMetrics(METRICS_DATA_FILE_NAME);
		Collections.sort(testClassesMetricsData, TLOC_COMPARATOR);
		printMoustacheBoxData(testClassesMetricsData, TLOC_METRIC_NAME);

		Collections.sort(testClassesMetricsData, WMC_COMPARATOR);
		// testClassesMetricsData.stream().forEach(o -> System.out.println(o));
		printMoustacheBoxData(testClassesMetricsData, WMC_METRIC_NAME);

		Collections.sort(testClassesMetricsData, TASSERT_COMPARATOR);
		printMoustacheBoxData(testClassesMetricsData, TASSERT_METRIC_NAME);
	}

	public static void printMoustacheBoxData(List<TestClassMetricsData> testClassesMetricsData, String metricName) {
		int medianeIndex = testClassesMetricsData.size() / 2;
		int quartileInferieurIndex = medianeIndex - (testClassesMetricsData.size() / 4) - 1;
		int quartileSuperieurIndex = medianeIndex + (testClassesMetricsData.size() / 4) + 1;
		double medianeValue = getMetricVal(testClassesMetricsData.get(medianeIndex), metricName);
		double quartileInferieurValue = getMetricVal(testClassesMetricsData.get(quartileInferieurIndex), metricName);
		double quartileSuperieurValue = getMetricVal(testClassesMetricsData.get(quartileSuperieurIndex), metricName);
		double longueurBoite = quartileSuperieurValue - quartileInferieurValue;
		double limiteSuperieur = quartileSuperieurValue + LIMIT_CONST * longueurBoite;
		double limiteInferieur = quartileInferieurValue - +LIMIT_CONST * longueurBoite;
		limiteInferieur = limiteInferieur < 0 ? 0 : limiteInferieur;
		System.out.printf("%s mediane value : %f\n", metricName, medianeValue);
		System.out.printf("%s quartile inferieur value : %f\n", metricName, quartileInferieurValue);
		System.out.printf("%s quartile superieur value : %f\n", metricName, quartileSuperieurValue);
		System.out.printf("%s limite inferieur value : %f\n", metricName, limiteInferieur);
		System.out.printf("%s limite superieur value : %f\n\n\n", metricName, limiteSuperieur);
	}

	public static double getMetricVal(TestClassMetricsData classMetricContainer, String metricName) {
		switch (metricName) {
		case TLOC_METRIC_NAME:
			return classMetricContainer.getTloc();
		case WMC_METRIC_NAME:
			return classMetricContainer.getWmc();
		case TASSERT_METRIC_NAME:
			return classMetricContainer.getTassert();
		default:
			return -1;
		}
	}

	public static List<TestClassMetricsData> parseMetrics(String fileName) throws IOException {
		List<String> metricsData = FileManipulation.readLines(METRICS_DATA_FILE_NAME);
		List<TestClassMetricsData> testClassesMetricsData = new ArrayList<TestClassMetricsData>();
		for (int x = 1; x < metricsData.size(); x++) {
			String[] metrics = metricsData.get(x).split(",");
			String className = metrics[0];
			double tloc = Double.parseDouble(metrics[1]);
			double wmc = Double.parseDouble(metrics[2]);
			double tassert = Double.parseDouble(metrics[3]);
			testClassesMetricsData.add(new TestClassMetricsData(className, tloc, wmc, tassert));
		}
		return testClassesMetricsData;
	}
}

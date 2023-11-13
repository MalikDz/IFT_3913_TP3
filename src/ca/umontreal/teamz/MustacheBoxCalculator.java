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
	private static final String METRICS_DATA_FILE_NAME = "jfreechart-test-stats.csv";
	private static final Comparator<TestClassMetricsData> TLOC_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return o1.getTloc() - o2.getTloc();
		}
	};
	private static final Comparator<TestClassMetricsData> WMC_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return o1.getWmc() - o2.getWmc();
		}
	};
	private static final Comparator<TestClassMetricsData> TASSERT_COMPARATOR = new Comparator<TestClassMetricsData>() {
		@Override
		public int compare(TestClassMetricsData o1, TestClassMetricsData o2) {
			return o1.getTassert() - o2.getTassert();
		}
	};

	public static void main(String[] args) throws IOException {
		List<TestClassMetricsData> testClassesMetricsData = parseMetrics(METRICS_DATA_FILE_NAME);
		Collections.sort(testClassesMetricsData, TLOC_COMPARATOR);
		printMoustacheBoxData(testClassesMetricsData, "Tloc");

		Collections.sort(testClassesMetricsData, WMC_COMPARATOR);
		printMoustacheBoxData(testClassesMetricsData, "Wmc");

		Collections.sort(testClassesMetricsData, TASSERT_COMPARATOR);
		printMoustacheBoxData(testClassesMetricsData, "Tassert");
	}

	public static void printMoustacheBoxData(List<TestClassMetricsData> testClassesMetricsData, String metricName) {
		int medianeIndex = testClassesMetricsData.size() / 2;
		int quartileInferieurIndex = medianeIndex - (testClassesMetricsData.size() / 4);
		int quartileSuperieurIndex = medianeIndex + (testClassesMetricsData.size() / 4);
		int medianeValue = testClassesMetricsData.get(medianeIndex).getTloc();
		int quartileInferieurValue = testClassesMetricsData.get(quartileInferieurIndex).getTloc();
		int quartileSuperieurValue = testClassesMetricsData.get(quartileSuperieurIndex).getTloc();
		double longueurBoite = quartileSuperieurValue - quartileInferieurValue;
		double limiteSuperieur = quartileSuperieurValue + LIMIT_CONST * longueurBoite;
		double limiteInferieur = quartileInferieurValue - +LIMIT_CONST * longueurBoite;
		limiteInferieur = limiteInferieur < 0 ? 0 : limiteInferieur;
		System.out.printf("%s mediane value : %d\n", metricName, medianeValue);
		System.out.printf("%s quartile inferieur value : %d\n", metricName, quartileInferieurValue);
		System.out.printf("%s quartile superieur value : %d\n", metricName, quartileSuperieurValue);
		System.out.printf("%s limite inferieur value : %f\n", metricName, limiteInferieur);
		System.out.printf("%s limite superieur value : %f\n\n\n", metricName, limiteSuperieur);
	}

	public static List<TestClassMetricsData> parseMetrics(String fileName) throws IOException {
		List<String> metricsData = FileManipulation.readLines(METRICS_DATA_FILE_NAME);
		List<TestClassMetricsData> testClassesMetricsData = new ArrayList<TestClassMetricsData>();
		for (int x = 1; x < metricsData.size(); x++) {
			String[] metrics = metricsData.get(x).split(",");
			String className = metrics[0];
			int tloc = Integer.parseInt(metrics[1]);
			int wmc = Integer.parseInt(metrics[2]);
			int tassert = Integer.parseInt(metrics[3]);
			testClassesMetricsData.add(new TestClassMetricsData(className, tloc, wmc, tassert));
		}
		return testClassesMetricsData;
	}
}

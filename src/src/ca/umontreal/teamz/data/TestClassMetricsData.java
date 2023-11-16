package ca.umontreal.teamz.data;

/**
 *
 * @author Malik Abada (matricule 20173066)
 * @author Christian Lungescu (matricule 20079725)
 * 
 */

public class TestClassMetricsData {
	private String className;
	private double tloc, wmc, tassert;

	public TestClassMetricsData(String className, double tloc, double wmc, double tassert) {
		this.tloc = tloc;
		this.wmc = wmc;
		this.tassert = tassert;
		this.className = className;
	}

	public double getTloc() {
		return tloc;
	}

	public double getWmc() {
		return wmc;
	}

	public double getTassert() {
		return tassert;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return String.format("Class name : %s  ,    tloc : %f    ,    wmc : %f   ,    tassert : %f", className, tloc,
				wmc, tassert);
	}
}

package ca.umontreal.teamz.data;

/**
 *
 * @author Malik Abada (matricule 20173066)
 * @author Christian Lungescu (matricule 20079725)
 * 
 */

public class TestClassMetricsData {
	private String className;
	private int tloc, wmc, tassert;

	public TestClassMetricsData(String className, int tloc, int wmc, int tassert) {
		this.tloc = tloc;
		this.wmc = wmc;
		this.tassert = tassert;
		this.className = className;
	}

	public int getTloc() {
		return tloc;
	}

	public int getWmc() {
		return wmc;
	}

	public int getTassert() {
		return tassert;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return String.format("Class name : %s  ,    tloc : %d    ,    wmc : %d    ,    tassert : %d ", className, tloc,
				wmc, tassert);
	}
}

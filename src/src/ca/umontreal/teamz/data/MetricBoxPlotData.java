package ca.umontreal.teamz.data;

public class MetricBoxPlotData {

	private double mediane;
	private String metricName;
	private double limiteSuperieur, limiteInferieur;
	private double quartileSuperieur, quartileInferieur;

	public MetricBoxPlotData(String metricName, double mediane, double limiteSuperieur, double limiteInferieur,
			double quartileSuperieur, double quartileInferieur) {
		this.mediane = mediane;
		this.metricName = metricName;
		this.limiteSuperieur = limiteSuperieur;
		this.limiteInferieur = limiteInferieur;
		this.quartileSuperieur = quartileSuperieur;
		this.quartileInferieur = quartileInferieur;
	}

	public String getMetricName() {
		return metricName;
	}

	public double getMediane() {
		return mediane;
	}

	public double getLimiteSuperieur() {
		return limiteSuperieur;
	}

	public double getLimiteInferieur() {
		return limiteInferieur;
	}

	public double getQuartileSuperieur() {
		return quartileSuperieur;
	}

	public double getQuartileInferieur() {
		return quartileInferieur;
	}

	@Override
	public String toString() {
		return String.format(
				"%s BOXPLOT:\nlimite inferieur value : %.2f\nquartile inferieur value : %.2f\nmediane value : %.2f\nquartile superieur value : %.2f\nlimite superieur value : %.2f\n\n\n",
				metricName.toUpperCase(), limiteInferieur, quartileInferieur, mediane, quartileSuperieur,
				limiteSuperieur);

	}
}

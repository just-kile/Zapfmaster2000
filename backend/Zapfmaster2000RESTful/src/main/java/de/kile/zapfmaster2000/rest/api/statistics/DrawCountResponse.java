package de.kile.zapfmaster2000.rest.api.statistics;

public class DrawCountResponse {

	/**
	 * Total number of drawings.
	 */
	private long drawCount;
	private double drawCountPerHour;

	public long getCount() {
		return drawCount;
	}

	public void setDrawCount(long count) {
		this.drawCount = count;
	}

	public double getDrawCountPerHour() {
		return drawCountPerHour;
	}

	public void setDrawCountPerHour(double drawCountPerHour) {
		this.drawCountPerHour = drawCountPerHour;
	}

}

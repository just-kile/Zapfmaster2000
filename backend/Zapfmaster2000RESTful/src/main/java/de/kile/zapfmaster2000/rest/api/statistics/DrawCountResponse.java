package de.kile.zapfmaster2000.rest.api.statistics;

public class DrawCountResponse {

	/**
	 * Total number of drawings.
	 */
	private long count;
	private double averageOperationsPerHour;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getAverageOperationsPerHour() {
		return averageOperationsPerHour;
	}

	public void setAverageOperationsPerHour(double averageOperationsPerHour) {
		this.averageOperationsPerHour = averageOperationsPerHour;
	}

}

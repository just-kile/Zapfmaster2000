package de.kile.zapfmaster2000.rest.api.statistics;

public class AmountResponse {
	/**
	 * The overall amount drunk.
	 */
	private double amountTotal;
	private double greatestDrawing;
	private int mostActivityHour;

	public double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public double getGreatestDrawing() {
		return greatestDrawing;
	}

	public void setGreatestDrawing(double greatestDrawing) {
		this.greatestDrawing = greatestDrawing;
	}

	public int getMostActivityHour() {
		return mostActivityHour;
	}

	public void setMostActivityHour(int mostActivityHour) {
		this.mostActivityHour = mostActivityHour;
	}

}

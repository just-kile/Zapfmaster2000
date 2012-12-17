package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class DrinkProgressResponse {
	private double[] amount;

	/**
	 * in minutes
	 */
	private int interval;
	private Date from;

	public double[] getAmount() {
		return amount;
	}

	public void setAmount(double[] amount) {
		this.amount = amount;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}
}

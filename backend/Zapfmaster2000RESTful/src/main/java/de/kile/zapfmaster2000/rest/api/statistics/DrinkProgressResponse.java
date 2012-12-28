package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class DrinkProgressResponse {
	private double[] data;

	/**
	 * in minutes
	 */
	private int interval;
	private Date startDate;

	public double[] getAmount() {
		return data;
	}

	public void setAmount(double[] amount) {
		this.data = amount;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Date getFrom() {
		return startDate;
	}

	public void setFrom(Date from) {
		this.startDate = from;
	}
}

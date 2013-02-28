package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;

public class DrinkProgressResponse {
	private double[] amount;

	/**
	 * in minutes
	 */
	private int interval;
	private String startDate;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(Date startDate) {
		SimpleDateFormat sf = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);

		this.startDate = sf.format(startDate);

	}
}

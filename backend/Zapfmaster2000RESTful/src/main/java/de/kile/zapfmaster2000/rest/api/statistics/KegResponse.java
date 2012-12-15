package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class KegResponse {

	/**
	 * Id of the current keg.
	 */
	private long kegId;
	private String brand;
	private int size;
	private Date start_date;
	private double currentAmount;

	/**
	 * Number of kegs drunk so far (including the current one)
	 */
	private long kegNumbers;

	public long getKegId() {
		return kegId;
	}

	public void setKegId(long kegId) {
		this.kegId = kegId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public long getKegNumber() {
		return kegNumbers;
	}

	public void setKegNumbers(long kegNumbers) {
		this.kegNumbers = kegNumbers;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

}

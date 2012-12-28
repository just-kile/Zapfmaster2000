package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class KegResponse {

	/**
	 * Id of the current keg.
	 */
	private long kegId;
	private String brand;
	private int size;
	private Date startDate;
	private double currentAmount;

	/**
	 * Number of kegs drunk so far (including the current one)
	 */
	private long kegNumber;
	private Date lastsUntil;

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
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public long getKegNumber() {
		return kegNumber;
	}

	public void setKegNumber(long kegNumber) {
		this.kegNumber = kegNumber;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Date getLastsUntil() {
		return lastsUntil;
	}

	public void setLastsUntil(Date lastsUntil) {
		this.lastsUntil = lastsUntil;
	}

}

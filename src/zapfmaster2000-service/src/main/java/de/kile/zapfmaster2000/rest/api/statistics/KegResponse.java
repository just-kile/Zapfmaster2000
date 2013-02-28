package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;

public class KegResponse {

	/**
	 * Id of the current keg.
	 */
	private long kegId;
	private String brand;
	private int size;
	private String startDate;
	private double currentAmount;
	private long boxId;

	/**
	 * Number of kegs drunk so far (including the current one)
	 */
	private long kegNumber;
	private String lastsUntil;

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

	public String getLastsUntil() {
		return lastsUntil;
	}

	public void setLastsUntil(String lastsUntil) {
		this.lastsUntil = lastsUntil;
	}

	public void setLastsUntil(Date lastsUntil) {
		SimpleDateFormat sf = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);

		this.lastsUntil = sf.format(lastsUntil);

	}

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

}

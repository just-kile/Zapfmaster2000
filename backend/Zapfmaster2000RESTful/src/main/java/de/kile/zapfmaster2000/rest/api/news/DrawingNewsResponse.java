package de.kile.zapfmaster2000.rest.api.news;

public class DrawingNewsResponse extends AbstractNewsResponse {

	private long userId;

	private String userName;

	private double amount;

	private long kegId;

	private String brand;

	private String location;

	public DrawingNewsResponse() {
		setType(Type.DRAWING);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

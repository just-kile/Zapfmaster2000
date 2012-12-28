package de.kile.zapfmaster2000.rest.api.statistics;

public class UserAmountResponse {
	
	private String userName;
	
	private long userId;
	
	private String userImage;
	
	private double amount;

	public String getName() {
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public long getId() {
		return userId;
	}

	public void setId(long id) {
		this.userId = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getImage() {
		return userImage;
	}

	public void setImage(String image) {
		this.userImage = image;
	}
	
	

}

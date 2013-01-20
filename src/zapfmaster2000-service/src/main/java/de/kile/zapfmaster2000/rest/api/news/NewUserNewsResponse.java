package de.kile.zapfmaster2000.rest.api.news;

public class NewUserNewsResponse extends AbstractNewsResponse {

	private String userName;

	private long userId;

	public NewUserNewsResponse() {
		super(Type.NEW_USER);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}

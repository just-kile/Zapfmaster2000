package de.kile.zapfmaster2000.rest.api.push;

public class LoginDraftKitResponse extends AbstractDraftKitResponse {

	private long userId;

	private String userName;

	private String imagePath;

	public LoginDraftKitResponse() {
		setType(Type.LOGIN);
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}

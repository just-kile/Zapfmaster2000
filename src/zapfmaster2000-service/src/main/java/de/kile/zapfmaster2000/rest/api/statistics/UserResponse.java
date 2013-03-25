package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class UserResponse {

	/**
	 * Id of the current keg.
	 */
	private long userId;
	private String userName;
	private String userImage;
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
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	/**
	 * Number of kegs drunk so far (including the current one)
	 */


}

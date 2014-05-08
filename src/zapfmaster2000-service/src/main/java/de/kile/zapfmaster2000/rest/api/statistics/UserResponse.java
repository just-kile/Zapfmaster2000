package de.kile.zapfmaster2000.rest.api.statistics;


import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;

public class UserResponse {

	/**
	 * Id of the current keg.
	 */
	private long userId;
	private String userName;
	private String userImage;
    private Sex sex;

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
    public Sex getSex() {
        return sex;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
    }


	/**
	 * Number of kegs drunk so far (including the current one)
	 */


}

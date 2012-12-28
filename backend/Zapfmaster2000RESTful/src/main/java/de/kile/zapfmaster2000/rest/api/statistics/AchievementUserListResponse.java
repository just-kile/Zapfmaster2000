package de.kile.zapfmaster2000.rest.api.statistics;

public class AchievementUserListResponse {
	private String userName;
	
	private long userId;
	
	private String userImage;
	
	private long count;

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

	public String getImage() {
		return userImage;
	}

	public void setImage(String image) {
		this.userImage = image;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}

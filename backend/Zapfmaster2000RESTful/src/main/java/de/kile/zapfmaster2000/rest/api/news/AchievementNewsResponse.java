package de.kile.zapfmaster2000.rest.api.news;

public class AchievementNewsResponse extends AbstractNewsResponse {

	public AchievementNewsResponse() {
		super(Type.ACHIEVEMENT);
	}

	private String achievementName;

	private long achievementId;

	private String userName;

	private long userId;
	
	

	public String getAchievementName() {
		return achievementName;
	}

	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	public long getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
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

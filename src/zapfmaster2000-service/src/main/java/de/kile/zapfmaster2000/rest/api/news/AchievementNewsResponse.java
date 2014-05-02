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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ (int) (achievementId ^ (achievementId >>> 32));
		result = prime * result
				+ ((achievementName == null) ? 0 : achievementName.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AchievementNewsResponse other = (AchievementNewsResponse) obj;
		if (achievementId != other.achievementId)
			return false;
		if (achievementName == null) {
			if (other.achievementName != null)
				return false;
		} else if (!achievementName.equals(other.achievementName))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}

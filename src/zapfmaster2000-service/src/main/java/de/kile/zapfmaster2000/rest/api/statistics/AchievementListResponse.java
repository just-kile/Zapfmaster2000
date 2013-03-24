package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Date;

public class AchievementListResponse {

	/**
	 * Overall number of achievements gained.
	 */
	private long achievementId;
	

	private String achievementName;
	private String achievementDescription;
	private String achievementImage;
	private Date date;
	public long getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
	}
	public String getAchievementName() {
		return achievementName;
	}
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}
	public String getAchievementDescription() {
		return achievementDescription;
	}
	public void setAchievementDescription(String achievementDescription) {
		this.achievementDescription = achievementDescription;
	}
	public String getAchievementImage() {
		return achievementImage;
	}
	public void setAchievementImage(String achievementImage) {
		this.achievementImage = achievementImage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	

}

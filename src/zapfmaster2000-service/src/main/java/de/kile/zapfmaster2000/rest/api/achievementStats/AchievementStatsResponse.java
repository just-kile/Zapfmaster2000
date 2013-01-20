package de.kile.zapfmaster2000.rest.api.achievementStats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;

public class AchievementStatsResponse {

	private String achievementName;

	private long achievementId;

	private String achievementImage;

	private String description;

	private final List<UserThatGained> users = new ArrayList<>();

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

	public String getAchievementImage() {
		return achievementImage;
	}

	public void setAchievementImage(String achievementImage) {
		this.achievementImage = achievementImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserThatGained> getUsers() {
		return users;
	}

	public static class UserThatGained {

		private long userId;

		private String userName;

		private String userImage;

		private String date;

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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public void loadDate(Date pDate) {
			if (pDate != null) {
				SimpleDateFormat format = new SimpleDateFormat(
						PlatformConstants.DATE_TIME_FORMAT);
				date = format.format(pDate);
			} else {
				date = null;
			}
		}
	}

}

package de.kile.zapfmaster2000.rest.api.members;

import java.util.ArrayList;
import java.util.List;

public class MemberResponse {

	private String userName;

	private long userId;

	private double totalAmount;

	private String imagePath;

	private final List<GainedUserAchievement> achievements = new ArrayList<>();

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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<GainedUserAchievement> getAchievements() {
		return achievements;
	}

	public static class GainedUserAchievement {

		private String achievementName;

		private String imagePath;

		private long achievementId;

		public String getAchievementName() {
			return achievementName;
		}

		public void setAchievementName(String achievementName) {
			this.achievementName = achievementName;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public long getAchievementId() {
			return achievementId;
		}

		public void setAchievementId(long achievementId) {
			this.achievementId = achievementId;
		}

	}

}

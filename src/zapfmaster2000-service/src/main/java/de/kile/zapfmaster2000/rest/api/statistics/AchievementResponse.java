package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.List;

public class AchievementResponse {

	/**
	 * Overall number of achievements gained.
	 */
	private long count;
	
	/**
	 * Most achievement gains per hour.
	 * -1 if there are no achievements at all.
	 */
	private int mostAchievementHour;

	private List<AchievementListResponse> achievements;
	// private double achievementspeed;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getMostAchievementHour() {
		return mostAchievementHour;
	}

	public void setMostAchievementHour(int mostAchievementHour) {
		this.mostAchievementHour = mostAchievementHour;
	}

	public List<AchievementListResponse> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementListResponse> achievements) {
		this.achievements = achievements;
	}

}

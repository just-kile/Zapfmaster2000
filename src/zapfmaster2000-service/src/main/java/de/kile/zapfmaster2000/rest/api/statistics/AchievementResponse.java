package de.kile.zapfmaster2000.rest.api.statistics;

public class AchievementResponse {

	/**
	 * Overall number of achievements gained.
	 */
	private long achievementCount;
	
	/**
	 * Most achievement gains per hour.
	 * -1 if there are no achievements at all.
	 */
	private int achievementMostAchievementHour;

	// private double achievementspeed;

	public long getAchievementCount() {
		return achievementCount;
	}

	public void setAchievementCount(long count) {
		this.achievementCount = count;
	}

	public int getAchievementMostAchievementHour() {
		return achievementMostAchievementHour;
	}

	public void setAchievementMostAchievementHour(int mostAchievementHour) {
		this.achievementMostAchievementHour = mostAchievementHour;
	}

}

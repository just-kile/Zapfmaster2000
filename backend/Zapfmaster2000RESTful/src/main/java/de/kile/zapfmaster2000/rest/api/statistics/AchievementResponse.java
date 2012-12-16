package de.kile.zapfmaster2000.rest.api.statistics;

public class AchievementResponse {

	/**
	 * Overall number of achievements gained.
	 */
	private long count;
	
	/**
	 * Most achievement gains per hour.
	 */
	private int mostAchievementHour;

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

}

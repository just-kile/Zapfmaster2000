package de.kile.zapfmaster2000.rest.api.statistics;

public class RankResponse {

	private long rankAchievements;
	private long rankAmount;
	private long rankDrawCount;

	public long getAchievements() {
		return rankAchievements;
	}

	public void setRankAchievements(long rankAchievements) {
		this.rankAchievements = rankAchievements;
	}

	public long getRankAmount() {
		return rankAmount;
	}

	public void setRankAmount(long rankAmount) {
		this.rankAmount = rankAmount;
	}

	public long getRankDrawCount() {
		return rankDrawCount;
	}

	public void setRankDrawCount(long rankDrawCount) {
		this.rankDrawCount = rankDrawCount;
	}

}

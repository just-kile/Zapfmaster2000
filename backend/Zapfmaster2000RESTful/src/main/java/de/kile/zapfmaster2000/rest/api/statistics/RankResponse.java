package de.kile.zapfmaster2000.rest.api.statistics;

public class RankResponse {

	private long achievements;
	private long amount;
	private long drawCount;

	public long getAchievements() {
		return achievements;
	}

	public void setAchievements(long achievements) {
		this.achievements = achievements;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(long drawCount) {
		this.drawCount = drawCount;
	}

}

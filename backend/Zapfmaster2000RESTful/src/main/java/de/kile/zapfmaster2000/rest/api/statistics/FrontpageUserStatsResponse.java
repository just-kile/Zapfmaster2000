package de.kile.zapfmaster2000.rest.api.statistics;

public class FrontpageUserStatsResponse {

	private AmountResponse amount;
	private AchievementResponse achievement;
	private DrawCountResponse drawCount;
	private RankResponse rank;

	public AmountResponse getAmount() {
		return amount;
	}

	public void setAmount(AmountResponse amount) {
		this.amount = amount;
	}

	public AchievementResponse getAchievement() {
		return achievement;
	}

	public void setAchievement(AchievementResponse achievement) {
		this.achievement = achievement;
	}

	public DrawCountResponse getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(DrawCountResponse drawCount) {
		this.drawCount = drawCount;
	}

	public RankResponse getRank() {
		return rank;
	}

	public void setRank(RankResponse rank) {
		this.rank = rank;
	}

}

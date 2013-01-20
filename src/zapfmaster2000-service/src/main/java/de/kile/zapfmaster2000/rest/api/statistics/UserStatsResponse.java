package de.kile.zapfmaster2000.rest.api.statistics;

public class UserStatsResponse {

	private AmountResponse amount;
	private AchievementResponse achievement;
	private DrawCountResponse drawCount;
	private AlcoholLevelResponse promille;
	private DrinkProgressResponse progress;
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

	public AlcoholLevelResponse getPromille() {
		return promille;
	}

	public void setPromille(AlcoholLevelResponse promille) {
		this.promille = promille;
	}

	public DrinkProgressResponse getProgress() {
		return progress;
	}

	public void setProgress(DrinkProgressResponse progress) {
		this.progress = progress;
	}

	public RankResponse getRank() {
		return rank;
	}

	public void setRank(RankResponse rank) {
		this.rank = rank;
	}

}

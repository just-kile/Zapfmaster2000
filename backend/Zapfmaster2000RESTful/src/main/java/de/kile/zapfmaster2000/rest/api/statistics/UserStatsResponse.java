package de.kile.zapfmaster2000.rest.api.statistics;

public class UserStatsResponse {

	private AmountResponse amount;
	private AchievementResponse achievement;
	private DrawCountResponse drawCount;
	private AlcoholLevelResponse alcoholLevel;
	private DrinkProgressResponse drinkProgress;
	private RankResponse rank;
	private UserResponse user;

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

	public AlcoholLevelResponse getAlcoholLevel() {
		return alcoholLevel;
	}

	public void setAlcoholLevel(AlcoholLevelResponse alcoholLevel) {
		this.alcoholLevel = alcoholLevel;
	}

	public DrinkProgressResponse getDrinkProgress() {
		return drinkProgress;
	}

	public void setDrinkProgress(DrinkProgressResponse drinkProgress) {
		this.drinkProgress = drinkProgress;
	}

	public RankResponse getRank() {
		return rank;
	}

	public void setRank(RankResponse rank) {
		this.rank = rank;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

}

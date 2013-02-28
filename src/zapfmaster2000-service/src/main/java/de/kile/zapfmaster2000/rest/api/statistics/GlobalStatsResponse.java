package de.kile.zapfmaster2000.rest.api.statistics;

public class GlobalStatsResponse {
	private KegResponse[] keg;
	private AmountResponse amount;
	private AchievementResponse achievements;
	private DrawCountResponse drawCount;
	private DrawCountUserListResponse[] drawCountUserList;
	private UserAmountResponse[] amountUserList;
	private UserAmountResponse[] amountUserListLastHour;
	private AchievementUserListResponse[] achievementUserList;
	private DrinkProgressResponse drinkProgress;
	private AlcoholLevelResponse alcoholLevel;

	public KegResponse[] getKeg() {
		return keg;
	}

	public void setKeg(KegResponse[] keg) {
		this.keg = keg;
	}

	public AmountResponse getAmount() {
		return amount;
	}

	public void setAmount(AmountResponse amount) {
		this.amount = amount;
	}

	public DrawCountResponse getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(DrawCountResponse drawCount) {
		this.drawCount = drawCount;
	}

	public DrawCountUserListResponse[] getDrawCountUserList() {
		return drawCountUserList;
	}

	public void setDrawCountUserList(
			DrawCountUserListResponse[] drawCountUserList) {
		this.drawCountUserList = drawCountUserList;
	}

	public UserAmountResponse[] getAmountUserList() {
		return amountUserList;
	}

	public void setAmountUserList(UserAmountResponse[] amountUserList) {
		this.amountUserList = amountUserList;
	}

	public AchievementUserListResponse[] getAchievementUserList() {
		return achievementUserList;
	}

	public void setAchievementUserList(
			AchievementUserListResponse[] achievementUserList) {
		this.achievementUserList = achievementUserList;
	}

	public DrinkProgressResponse getDrinkProgress() {
		return drinkProgress;
	}

	public void setDrinkProgress(DrinkProgressResponse drinkProgress) {
		this.drinkProgress = drinkProgress;
	}

	public AlcoholLevelResponse getAlcoholLevel() {
		return alcoholLevel;
	}

	public void setAlcoholLevel(AlcoholLevelResponse alcoholLevel) {
		this.alcoholLevel = alcoholLevel;
	}

	public UserAmountResponse[] getAmountUserListLastHour() {
		return amountUserListLastHour;
	}

	public void setAmountUserListHour(UserAmountResponse[] amountUserListHour) {
		this.amountUserListLastHour = amountUserListHour;
	}

	public AchievementResponse getAchievements() {
		return achievements;
	}

	public void setAchievements(AchievementResponse achievements) {
		this.achievements = achievements;
	}

}

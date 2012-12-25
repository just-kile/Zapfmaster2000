package de.kile.zapfmaster2000.rest.api.statistics;

public class GlobalStatsResponse {
	private KegResponse[] keg;
	private AmountResponse amount;
	private DrawCountResponse drawCount;
	private DrawCountUserListResponse[] drawCountUserList;
	private UserAmountResponse[] bestUserList;
	private UserAmountResponse[] bestUserListHour;
	private AchievementUserListResponse[] achievementUserList;
	private DrinkProgressResponse progress;
	private AlcoholLevelResponse promille;

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

	public UserAmountResponse[] getBestUserList() {
		return bestUserList;
	}

	public void setBestUserList(UserAmountResponse[] bestUserList) {
		this.bestUserList = bestUserList;
	}

	public AchievementUserListResponse[] getAchievementUserList() {
		return achievementUserList;
	}

	public void setAchievementUserList(
			AchievementUserListResponse[] achievementUserList) {
		this.achievementUserList = achievementUserList;
	}

	public DrinkProgressResponse getProgress() {
		return progress;
	}

	public void setProgress(DrinkProgressResponse progress) {
		this.progress = progress;
	}

	public AlcoholLevelResponse getPromille() {
		return promille;
	}

	public void setPromille(AlcoholLevelResponse promille) {
		this.promille = promille;
	}

	public UserAmountResponse[] getBestUserListHour() {
		return bestUserListHour;
	}

	public void setBestUserListHour(UserAmountResponse[] bestUserListHour) {
		this.bestUserListHour = bestUserListHour;
	}

}

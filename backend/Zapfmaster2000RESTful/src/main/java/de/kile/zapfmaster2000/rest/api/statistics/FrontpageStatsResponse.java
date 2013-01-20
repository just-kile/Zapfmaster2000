package de.kile.zapfmaster2000.rest.api.statistics;

public class FrontpageStatsResponse {

	private KegResponse[] kegs;
	private DrawCountUserListResponse[] drawCountUserList;
	private UserAmountResponse[] amountUserList;
	private UserAmountResponse[] amountUserListLastHour;
	private AchievementUserListResponse[] achievementUserList;
	private AlcoholLevelResponse alcoholLevel;

	public KegResponse[] getKegs() {
		return kegs;
	}

	public void setKegs(KegResponse[] kegs) {
		this.kegs = kegs;
	}

	public UserAmountResponse[] getBestUserList() {
		return amountUserList;
	}

	public void setAmountUserList(UserAmountResponse[] amountUserList) {
		this.amountUserList = amountUserList;
	}

	public UserAmountResponse[] getAmountUserListLastHour() {
		return amountUserListLastHour;
	}

	public void setAmountUserListLastHour(
			UserAmountResponse[] amountUserListLastHour) {
		this.amountUserListLastHour = amountUserListLastHour;
	}

	public AchievementUserListResponse[] getAchievementUserList() {
		return achievementUserList;
	}

	public void setAchievementUserList(
			AchievementUserListResponse[] achievementUserList) {
		this.achievementUserList = achievementUserList;
	}

	public AlcoholLevelResponse getAlcoholLevel() {
		return alcoholLevel;
	}

	public void setAlcoholLevel(AlcoholLevelResponse alcoholLevel) {
		this.alcoholLevel = alcoholLevel;
	}

	public DrawCountUserListResponse[] getDrawCountUserList() {
		return drawCountUserList;
	}

	public void setDrawCountUserList(
			DrawCountUserListResponse[] drawCountUserList) {
		this.drawCountUserList = drawCountUserList;
	}

}

package de.kile.zapfmaster2000.rest.api.statistics;

public class FrontpageStatsResponse {

	private KegResponse[] kegs;
	private DrawCountUserListResponse[] drawCountUserList;
	private UserAmountResponse[] bestUserList;
	private UserAmountResponse[] bestUserListHour;
	private AchievementUserListResponse[] achievementUserList;
	private AlcoholLevelResponse promille;

	public KegResponse[] getKegs() {
		return kegs;
	}

	public void setKegs(KegResponse[] kegs) {
		this.kegs = kegs;
	}

	public UserAmountResponse[] getBestUserList() {
		return bestUserList;
	}

	public void setBestUserList(UserAmountResponse[] bestUserList) {
		this.bestUserList = bestUserList;
	}

	public UserAmountResponse[] getBestUserListHour() {
		return bestUserListHour;
	}

	public void setBestUserListHour(UserAmountResponse[] bestUserListHour) {
		this.bestUserListHour = bestUserListHour;
	}

	public AchievementUserListResponse[] getAchievementUserList() {
		return achievementUserList;
	}

	public void setAchievementUserList(
			AchievementUserListResponse[] achievementUserList) {
		this.achievementUserList = achievementUserList;
	}

	public AlcoholLevelResponse getPromille() {
		return promille;
	}

	public void setPromille(AlcoholLevelResponse promille) {
		this.promille = promille;
	}

	public DrawCountUserListResponse[] getDrawCountUserList() {
		return drawCountUserList;
	}

	public void setDrawCountUserList(DrawCountUserListResponse[] drawCountUserList) {
		this.drawCountUserList = drawCountUserList;
	}


}

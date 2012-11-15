package de.zapfmaster2000.webservice.achievement.total.user;


public class Homer extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 15;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

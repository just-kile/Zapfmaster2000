package de.zapfmaster2000.webservice.achievement.total.user;


public class Lenny extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 10;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

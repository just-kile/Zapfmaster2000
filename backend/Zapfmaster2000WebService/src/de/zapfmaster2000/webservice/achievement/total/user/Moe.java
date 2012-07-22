package de.zapfmaster2000.webservice.achievement.total.user;


public class Moe extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 1;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

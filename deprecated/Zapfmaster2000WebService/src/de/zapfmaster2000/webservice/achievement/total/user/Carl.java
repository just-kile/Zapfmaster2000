package de.zapfmaster2000.webservice.achievement.total.user;


public class Carl extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 5;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

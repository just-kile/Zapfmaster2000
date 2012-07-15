package de.beeromat.internal.core.achievement.total.user;


public class Barney extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 25;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

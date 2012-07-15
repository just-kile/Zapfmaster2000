package de.beeromat.internal.core.achievement.total.user;


public class Moe extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 1;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

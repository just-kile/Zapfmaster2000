package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user;


public class Barney extends AbstractUserTotalAchievementProcessor {

	public static final int NUM_BEERS = 25;

	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}
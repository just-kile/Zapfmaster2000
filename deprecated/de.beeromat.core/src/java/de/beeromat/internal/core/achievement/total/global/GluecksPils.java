package de.beeromat.internal.core.achievement.total.global;

public class GluecksPils extends AbstractGlobalTotalAchievementProcessor {

	private static final int NUM_BEERS = 25;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}
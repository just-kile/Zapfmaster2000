package de.beeromat.internal.core.achievement.total.global;

public class VeniVidiBieri extends AbstractGlobalTotalAchievementProcessor {

	private static final int NUM_BEERS = 100;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

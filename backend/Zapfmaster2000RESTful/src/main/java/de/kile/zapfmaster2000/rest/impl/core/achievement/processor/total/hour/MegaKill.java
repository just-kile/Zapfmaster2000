package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.hour;

public class MegaKill extends AbstractTotalPerHourAchievementProcessor {

	private final static int NUM_BEERS = 2;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

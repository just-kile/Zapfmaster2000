package de.beeromat.internal.core.achievement.total.hour;

public class UltraKill extends AbstractTotalPerHourAchievementProcessor {

	private final static int NUM_BEERS = 3;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}
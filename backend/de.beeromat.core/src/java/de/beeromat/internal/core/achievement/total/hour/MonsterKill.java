package de.beeromat.internal.core.achievement.total.hour;

public class MonsterKill extends AbstractTotalPerHourAchievementProcessor {

	private final static int NUM_BEERS = 4;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

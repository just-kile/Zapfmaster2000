package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global;

public class Halbzeit extends AbstractGlobalTotalAchievementProcessor {

	private static final int NUM_BEERS = 200;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

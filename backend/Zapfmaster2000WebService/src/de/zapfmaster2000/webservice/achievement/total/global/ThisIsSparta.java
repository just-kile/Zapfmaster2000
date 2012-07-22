package de.zapfmaster2000.webservice.achievement.total.global;

public class ThisIsSparta extends AbstractGlobalTotalAchievementProcessor {

	private static final int NUM_BEERS = 300;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

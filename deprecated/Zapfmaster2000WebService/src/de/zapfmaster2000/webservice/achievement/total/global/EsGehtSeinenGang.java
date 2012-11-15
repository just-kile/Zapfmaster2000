package de.zapfmaster2000.webservice.achievement.total.global;

public class EsGehtSeinenGang extends AbstractGlobalTotalAchievementProcessor {

	private static final int NUM_BEERS = 50;
	
	@Override
	protected double getTotal() {
		return getBeerSize() * NUM_BEERS;
	}

}

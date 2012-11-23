package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.unique;

public class Nimmersatt extends AbstractUniqueDrawingAchievement {

	private static final int NUM_BEERS = 2;
	
	@Override
	protected double getAmount() {
		return getBeerSize() * NUM_BEERS;
	}

}

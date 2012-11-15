package de.beeromat.internal.core.achievement.total.unique;

public class Nimmersatt extends AbstractUniqueDrawingAchievement {

	private static final int NUM_BEERS = 2;
	
	@Override
	protected double getAmount() {
		return getBeerSize() * NUM_BEERS;
	}

}

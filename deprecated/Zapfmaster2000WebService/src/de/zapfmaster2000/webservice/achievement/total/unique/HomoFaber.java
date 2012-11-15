package de.zapfmaster2000.webservice.achievement.total.unique;

public class HomoFaber extends AbstractUniqueDrawingAchievement {

	private static final int NUM_BEERS = 4;
	
	@Override
	protected double getAmount() {
		return getBeerSize() * NUM_BEERS;
	}

}

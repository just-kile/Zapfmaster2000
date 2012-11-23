package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.degree;

public class Bierbachelor extends AbstractDegreeAchievement {

	private final static int NUM_BEERS = 3;
	
	@Override
	protected int getNumBeers() {
		return NUM_BEERS;
	}

}

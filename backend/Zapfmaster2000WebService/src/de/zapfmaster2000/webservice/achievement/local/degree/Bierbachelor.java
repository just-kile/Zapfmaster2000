package de.zapfmaster2000.webservice.achievement.local.degree;

public class Bierbachelor extends AbstractDegreeAchievement {

	private final static int NUM_BEERS = 3;
	
	@Override
	protected int getNumBeers() {
		return NUM_BEERS;
	}

}

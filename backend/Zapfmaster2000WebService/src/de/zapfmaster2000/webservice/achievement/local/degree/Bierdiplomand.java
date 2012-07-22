package de.zapfmaster2000.webservice.achievement.local.degree;

public class Bierdiplomand extends AbstractDegreeAchievement {

	private final static int NUM_BEERS = 5;
	
	@Override
	protected int getNumBeers() {
		return NUM_BEERS;
	}

}

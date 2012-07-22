package de.zapfmaster2000.webservice.achievement.local.degree;

public class Biermaster extends AbstractDegreeAchievement {

	private final static int NUM_BEERS = 4;
	
	@Override
	protected int getNumBeers() {
		return NUM_BEERS;
	}

}

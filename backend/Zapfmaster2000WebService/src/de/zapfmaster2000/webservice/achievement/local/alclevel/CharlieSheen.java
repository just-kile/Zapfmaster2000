package de.zapfmaster2000.webservice.achievement.local.alclevel;

public class CharlieSheen extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 3;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

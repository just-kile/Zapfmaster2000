package de.zapfmaster2000.webservice.achievement.local.alclevel;

public class LindsayLohan extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 1;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

package de.beeromat.internal.core.achievement.local.alclevel;

public class HaraldJunke extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 5;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

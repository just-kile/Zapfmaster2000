package de.beeromat.internal.core.achievement.local.alclevel;

public class HankMoody extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 2;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

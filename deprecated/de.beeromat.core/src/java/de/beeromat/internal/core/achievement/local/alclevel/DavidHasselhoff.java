package de.beeromat.internal.core.achievement.local.alclevel;

public class DavidHasselhoff extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 4;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

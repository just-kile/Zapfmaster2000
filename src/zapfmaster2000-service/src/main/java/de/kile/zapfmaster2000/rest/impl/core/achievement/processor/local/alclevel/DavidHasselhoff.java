package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel;

public class DavidHasselhoff extends AbstractAlcoholLevelAchievementProcessor {

	private static final double PER_MILLE_LEVEL = 4;
	
	@Override
	protected double getPerMille() {
		return PER_MILLE_LEVEL;
	}

}

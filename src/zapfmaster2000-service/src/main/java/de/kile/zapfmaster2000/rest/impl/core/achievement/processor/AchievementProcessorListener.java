package de.kile.zapfmaster2000.rest.impl.core.achievement.processor;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;

public interface AchievementProcessorListener {

	public void onAchievementGained(GainedAchievement pGainedAchievement);
	
}

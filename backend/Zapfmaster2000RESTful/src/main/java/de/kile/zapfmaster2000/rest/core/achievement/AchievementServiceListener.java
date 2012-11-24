package de.kile.zapfmaster2000.rest.core.achievement;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;

public interface AchievementServiceListener {
	
	public void onAchievementGained(GainedAchievement pGainedAchievement);

}

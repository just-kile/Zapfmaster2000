package de.kile.zapfmaster2000.rest.impl.core.achievement;

import java.util.ArrayList;
import java.util.List;

import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementServiceListener;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;

public class AchievementServiceImpl implements AchievementService {

	private final List<AchievementServiceListener> listeners = new ArrayList<>();

	public AchievementServiceImpl(BoxService pBoxService) {

		createAchievmentProcessors();
	}

	@Override
	public void addListener(AchievementServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(AchievementServiceListener pListener) {
		listeners.remove(pListener);
	}

	private void notifyAchivementGained(GainedAchievement pGainedAchievement) {
		for (AchievementServiceListener listener : listeners) {
			listener.onAchievementGained(pGainedAchievement);
		}
	}

	private void createAchievmentProcessors() {
		List<AchievementDescriptor> descriptors = new AchievementRegistry()
				.createDescriptors();
		
	}

	private void findAchievment(AchievementDescriptor pDescriptor) {

	}

}

package de.zapfmaster2000.webservice.achievement;

import java.util.List;

import de.zapfmaster2000.webservice.db.DBUtil;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractUniqueAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public boolean canGain(User pUser) {
		int achievementId = getAchievement().getAchievmentId();
		List<?> result = DBUtil
				.executeQuery("select a from GainedAchievement as a where a.achievementId = "
						+ achievementId);
		return result.isEmpty();
	}

}

package de.beeromat.core.achievemnt;

import java.util.List;

import de.beeromat.core.model.db.User;
import de.zapfmaster2000.webservice.db.DBUtil;

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

package de.zapfmaster2000.webservice.achievement.total;

import java.util.List;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.DBUtil;
import de.zapfmaster2000.webservice.model.db.Keg;
import de.zapfmaster2000.webservice.model.db.User;

public class FirstBlood extends AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		Keg keg = Zapfmaster2000Core.getInstance().getKegManager()
				.readCurrentKeg();
		List<?> results = DBUtil
				.executeQuery("select d from Drawing as d where d.kegId = "
						+ keg.getKegId());
		if (results.size() == 1) {
			gain(pUser);
		}
	}

}

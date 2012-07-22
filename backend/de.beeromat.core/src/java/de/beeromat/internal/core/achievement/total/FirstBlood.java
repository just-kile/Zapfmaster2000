package de.beeromat.internal.core.achievement.total;

import java.util.List;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.model.db.Keg;
import de.beeromat.core.model.db.User;
import de.zapfmaster2000.webservice.db.DBUtil;

public class FirstBlood extends AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		Keg keg = BeeromatCoreActivator.getDefault().getCore().getKegManager()
				.readCurrentKeg();
		List<?> results = DBUtil
				.executeQuery("select d from Drawing as d where d.kegId = "
						+ keg.getKegId());
		if (results.size() == 1) {
			gain(pUser);
		}
	}

}

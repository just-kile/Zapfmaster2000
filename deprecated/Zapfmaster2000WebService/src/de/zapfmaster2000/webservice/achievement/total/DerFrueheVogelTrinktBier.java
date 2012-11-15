package de.zapfmaster2000.webservice.achievement.total;

import java.util.Calendar;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.model.db.User;

public class DerFrueheVogelTrinktBier extends AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 8
				&& calendar.get(Calendar.HOUR_OF_DAY) < 12) {
			gain(pUser);
		}
	}

}

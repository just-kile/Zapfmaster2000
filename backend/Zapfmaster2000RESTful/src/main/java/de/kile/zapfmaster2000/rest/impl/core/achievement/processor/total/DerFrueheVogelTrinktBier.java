package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total;

import java.util.Calendar;

import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class DerFrueheVogelTrinktBier extends AbstractAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();
		
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.HOUR_OF_DAY) >= 8
				&& calendar.get(Calendar.HOUR_OF_DAY) < 12) {
			gain(user);
		}
	}

}

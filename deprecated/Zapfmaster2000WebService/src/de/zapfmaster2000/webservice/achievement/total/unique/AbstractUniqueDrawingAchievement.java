package de.zapfmaster2000.webservice.achievement.total.unique;

import java.util.List;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.config.ConfigKeys;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.DBUtil;
import de.zapfmaster2000.webservice.model.db.Drawing;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractUniqueDrawingAchievement extends
		AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		List<?> result = DBUtil
				.executeQuery("select d from Drawing as d where d.userId = "
						+ pUser.getUserId() + " order by d.date DESC");
		if (!result.isEmpty() && result.get(0) instanceof Drawing) {
			Drawing drawing = (Drawing) result.get(0);
			if (drawing.getRealAmount() >= getAmount()) {
				gain(pUser);
			}
		}

	}

	protected abstract double getAmount();

	protected final double getBeerSize() {
		ConfigManager configManager = Zapfmaster2000Core.getInstance()
				.getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

}

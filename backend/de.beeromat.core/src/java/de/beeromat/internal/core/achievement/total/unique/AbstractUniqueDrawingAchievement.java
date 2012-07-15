package de.beeromat.internal.core.achievement.total.unique;

import java.util.List;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.db.DBUtil;
import de.beeromat.core.model.db.Drawing;
import de.beeromat.core.model.db.User;

public abstract class AbstractUniqueDrawingAchievement extends
		AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		List<?> result = DBUtil.executeQuery("select d from Drawing as d where d.userId = "
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
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

}

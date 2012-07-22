package de.zapfmaster2000.webservice.achievement.total.global;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractUniqueAchievementProcessor;
import de.zapfmaster2000.webservice.config.ConfigKeys;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractGlobalTotalAchievementProcessor extends
		AbstractUniqueAchievementProcessor {

	@Override
	public void process(User pUser) {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		Query query = session
				.createQuery("select sum(d.realAmount) from Drawing as d");
		List<?> result = query.list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Double) {
			double sum = (Double) result.get(0);
			if (sum >= getTotal()) {
				gain(pUser);
			}
		}
	}

	protected final double getBeerSize() {
		ConfigManager configManager = Zapfmaster2000Core.getInstance()
				.getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract double getTotal();

}

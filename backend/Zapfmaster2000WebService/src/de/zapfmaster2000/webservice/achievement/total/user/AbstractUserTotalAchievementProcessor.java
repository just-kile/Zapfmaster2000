package de.zapfmaster2000.webservice.achievement.total.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.config.ConfigKeys;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractUserTotalAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		Query query = session
				.createQuery("select sum(d.realAmount) from Drawing as d where d.userId = "
						+ pUser.getUserId());
		List<?> result = query.list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Double) {
			double sum = (Double) result.get(0);
			if (sum >= getTotal()) {
				gain(pUser);
			}
		}
	}

	protected double getBeerSize() {
		ConfigManager configManager = Zapfmaster2000Core.getInstance()
				.getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract double getTotal();

}

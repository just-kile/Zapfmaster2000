package de.beeromat.internal.core.achievement.total.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.db.SessionFactoryUtil;
import de.beeromat.core.model.db.User;

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
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract double getTotal();

}

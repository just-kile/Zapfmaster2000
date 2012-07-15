package de.beeromat.internal.core.achievement.total.global;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractUniqueAchievementProcessor;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.db.SessionFactoryUtil;
import de.beeromat.core.model.db.User;

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
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract double getTotal();

}

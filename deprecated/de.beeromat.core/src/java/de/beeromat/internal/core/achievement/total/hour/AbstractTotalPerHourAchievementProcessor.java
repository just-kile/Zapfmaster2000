package de.beeromat.internal.core.achievement.total.hour;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.model.db.User;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

public abstract class AbstractTotalPerHourAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {

		final String query = "select sum(d.realAmount) from Drawing as d where d.userId = "
				+ pUser.getUserId() + " and d.date > :date1";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date date = calendar.getTime();

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();

		List<?> result = session.createQuery(query).setTimestamp("date1", date)
				.list();
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

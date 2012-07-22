package de.zapfmaster2000.webservice.achievement.total.hour;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.config.ConfigKeys;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.User;

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
		ConfigManager configManager = Zapfmaster2000Core.getInstance()
				.getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract double getTotal();

}

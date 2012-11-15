package de.zapfmaster2000.webservice.achievement.local.degree;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.config.ConfigKeys;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractDegreeAchievement extends
		AbstractAchievementProcessor {

	private static final int NUM_DAYS = 4;

	@Override
	public void process(User pUser) {
		Double[] values = new Double[NUM_DAYS];

		final String query = "select sum(d.realAmount) from Drawing as d where d.date > :date1 and d.date < :date2 and d.userId = "
				+ pUser.getUserId();
		Calendar current = Calendar.getInstance();
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.HOUR_OF_DAY, 8);
		current.add(Calendar.DAY_OF_YEAR, 1);

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		for (int i = 0; i < NUM_DAYS; ++i) {
			Calendar previous = (Calendar) current.clone();
			previous.add(Calendar.DAY_OF_YEAR, -1);
			List<?> result = session.createQuery(query)
					.setTimestamp("date1", previous.getTime())
					.setTimestamp("date2", current.getTime()).list();

			if (!result.isEmpty() && result.get(0) instanceof Double) {
				double sum = (Double) result.get(0);
				values[i] = sum;
			} else {
				values[i] = 0.0;
			}

			current.add(Calendar.DAY_OF_YEAR, -1);
		}
		tx.commit();

		for (int i = 0; i < NUM_DAYS; ++i) {
			if (values[i] < getBeerSize() * getNumBeers()) {
				return;
			}
		}

		gain(pUser);
	}

	protected final double getBeerSize() {
		ConfigManager configManager = Zapfmaster2000Core.getInstance()
				.getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

	protected abstract int getNumBeers();

}

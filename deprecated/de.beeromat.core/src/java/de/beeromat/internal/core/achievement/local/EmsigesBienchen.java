package de.beeromat.internal.core.achievement.local;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.model.db.User;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

public class EmsigesBienchen extends AbstractAchievementProcessor {

	private static final int NUM_HOURS = 5;

	@Override
	public void process(User pUser) {
		Double[] values = new Double[NUM_HOURS];

		final String query = "select sum(d.realAmount) from Drawing as d where d.date > :date1 and d.date < :date2 and d.userId = "
				+ pUser.getUserId();
		Calendar current = Calendar.getInstance();
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.add(Calendar.HOUR, 1);

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		for (int i = 0; i < NUM_HOURS; ++i) {
			Calendar previous = (Calendar) current.clone();
			previous.add(Calendar.HOUR, -1);
			List<?> result = session.createQuery(query)
					.setTimestamp("date1", previous.getTime())
					.setTimestamp("date2", current.getTime()).list();

			if (!result.isEmpty() && result.get(0) instanceof Double) {
				double sum = (Double) result.get(0);
				values[i] = sum;
			} else {
				values[i] = 0.0;
			}

			current.add(Calendar.HOUR, -1);
		}

		tx.commit();

		for (int i = 0; i < NUM_HOURS; ++i) {
			if (values[i] < getBeerSize()) {
				return;
			}
		}

		gain(pUser);
	}

	protected final double getBeerSize() {
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.BEER_SIZE);
	}

}

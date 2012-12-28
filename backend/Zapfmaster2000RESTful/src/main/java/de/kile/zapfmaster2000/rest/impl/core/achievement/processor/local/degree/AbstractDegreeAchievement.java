package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.degree;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public abstract class AbstractDegreeAchievement extends
		AbstractAchievementProcessor {

	private static final int NUM_DAYS = 4;

	@Override
	public void process(Drawing pDrawing) {
		Double[] values = new Double[NUM_DAYS];
		User user = pDrawing.getUser();

		final String query = "SELECT SUM(d.amount) FROM Drawing d "
				+ "WHERE d.date > :date1 AND d.date < :date2 "
				+ "AND d.user = :user";

		Calendar current = Calendar.getInstance();
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.HOUR_OF_DAY, 8);
		current.add(Calendar.DAY_OF_YEAR, 1);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		user = (User) session.load(Zapfmaster2000Package.eINSTANCE.getUser()
				.getName(), user.getId());

		for (int i = 0; i < NUM_DAYS; ++i) {
			Calendar previous = (Calendar) current.clone();
			previous.add(Calendar.DAY_OF_YEAR, -1);
			List<?> result = session.createQuery(query)
					.setTimestamp("date1", previous.getTime())
					.setTimestamp("date2", current.getTime())
					.setEntity("user", user).list();

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

		gain(user);
	}

	protected abstract int getNumBeers();

}

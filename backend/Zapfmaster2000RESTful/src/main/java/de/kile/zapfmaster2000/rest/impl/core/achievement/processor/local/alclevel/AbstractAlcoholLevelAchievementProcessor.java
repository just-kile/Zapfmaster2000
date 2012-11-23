package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public abstract class AbstractAlcoholLevelAchievementProcessor extends
		AbstractAchievementProcessor {

	private static final int NUM_HOURS = 12;

	@Override
	public void process(Drawing pDrawing) {
		Double[] values = new Double[NUM_HOURS];
		User user = pDrawing.getUser();

		final String query = "SELECT SUM(d.amount) FROM Drawing d "
				+ "WHERE d.date > :date1 AND d.date < :date2 "
				+ "AND d.user = :user";

		Calendar current = Calendar.getInstance();
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.add(Calendar.HOUR, 1);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		tx = session.beginTransaction();
		session.update(user);

		for (int i = 0; i < NUM_HOURS; ++i) {
			Calendar previous = (Calendar) current.clone();
			previous.add(Calendar.HOUR, -1);
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

			current.add(Calendar.HOUR, -1);
		}

		tx.commit();

		// Widmark Formel c = V*e*rho / (m*r)
		final double diffUser = 1.0;
		final double promConst = 0.05 * 0.8 / 0.65;
		double prom = 0;
		for (int i = 0; i < NUM_HOURS; ++i) {
			double hourProm = values[i] * promConst / (diffUser * 80) - i
					* 0.0001;
			if (hourProm > 0) {
				prom += hourProm;
			}
		}
		prom *= 1000;

		if (prom >= getPerMille()) {
			gain(user);
		}
	}

	protected abstract double getPerMille();

}

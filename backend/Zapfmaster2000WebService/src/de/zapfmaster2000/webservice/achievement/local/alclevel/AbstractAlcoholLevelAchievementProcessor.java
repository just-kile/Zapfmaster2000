package de.zapfmaster2000.webservice.achievement.local.alclevel;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractAchievementProcessor;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.User;

public abstract class AbstractAlcoholLevelAchievementProcessor extends
		AbstractAchievementProcessor {

	private static final int NUM_HOURS = 12;

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

		// Widmark Formel c = V*e*rho / (m*r)
		final double diffUser = 1.0;
		final double promConst =  0.05 * 0.8 / 0.65;
		double prom = 0;
		for (int i = 0; i < NUM_HOURS; ++i) {
			double hourProm = values[i] * promConst/(diffUser*80)- i * 0.0001;
			if (hourProm > 0) {
				prom += hourProm;
			}
		}
		prom *= 1000;
		
		if (prom >= getPerMille()) {
			gain(pUser);
		}
	}

	protected abstract double getPerMille();

}

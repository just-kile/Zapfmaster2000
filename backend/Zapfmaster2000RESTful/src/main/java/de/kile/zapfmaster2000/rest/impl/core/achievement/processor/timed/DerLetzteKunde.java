package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.timed;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractTimedAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class DerLetzteKunde extends AbstractTimedAchievementProcessor {

	@Override
	protected int getHour() {
		return 8;
	}

	@Override
	protected void doProcess() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		tx = session.beginTransaction();
		List<?> result = session
				.createQuery(
						"SELECT d FROM Drawing d JOIN d.user "
								+ "WHERE d.date > :date1 ORDER BY d.date DESC")
				.setTimestamp("date1", calendar.getTime()).setMaxResults(1)
				.list();
		tx.commit();

		if (!result.isEmpty()) {
			Object rawResult = result.get(0);
			if (rawResult instanceof Drawing) {
				Drawing drawing = (Drawing) rawResult;
				User user = drawing.getUser();
				if (canGain(user)) {
					gain(user);

				}
			}
		}
	}

}

package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class TrinkerDerStunde extends AbstractAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date date = calendar.getTime();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<?> result = session
				.createQuery(
						"SELECT d.user.id, SUM(d.amount) AS drawSum FROM Drawing d "
								+ "WHERE d.date > :date GROUP BY d.user.id "
								+ "ORDER BY drawSum DESC")
				.setTimestamp("date", date).setMaxResults(1).list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Object[]) {
			Object[] first = (Object[]) result.get(0);
			if (first.length == 2 && first[0] instanceof Integer) {
				Integer id = (Integer) first[0];
				if (id == user.getId()) {
					gain(user);
				}
			}
		}

	}

}

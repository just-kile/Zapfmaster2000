package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.timed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractTimedAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class Abstinenzler extends AbstractTimedAchievementProcessor {

	@Override
	protected int getHour() {
		return 8;
	}

	@Override
	protected void doProcess() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		final Date now = calendar.getTime();

		// TODO: optimize query: just one query needed for all users!
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		tx = session.beginTransaction();
		List<?> result = session.createQuery("FROM user").list();
		tx.commit();

		for (Object rawResult : result) {
			if (rawResult instanceof User) {
				User user = (User) rawResult;
				if (user.getType() != UserType.GUEST && canGain(user)) {

					session = Zapfmaster2000Core.INSTANCE
							.getTransactionService().getSessionFactory()
							.getCurrentSession();
					session.update(user);
					tx = session.beginTransaction();
					List<?> result2 = session
							.createQuery(
									"SELECT COUNT(*) FROM Drawing d "
											+ "WHERE d.date > :date1 and d.user = :user")
							.setTimestamp("date1", now).setEntity("user", user)
							.list();
					tx.commit();
					int count = (Integer) result2.get(0);
					if (count == 0) {
						gain(user);
					}
				}
			}
		}

	}

}

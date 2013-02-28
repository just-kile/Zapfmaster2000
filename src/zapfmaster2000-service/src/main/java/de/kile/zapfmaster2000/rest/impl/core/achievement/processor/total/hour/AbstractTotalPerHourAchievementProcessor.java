package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.hour;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public abstract class AbstractTotalPerHourAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date date = calendar.getTime();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		user = (User) session.load(Zapfmaster2000Package.eINSTANCE.getUser()
				.getName(), user.getId());
		List<?> result = session
				.createQuery(
						"SELECT SUM(d.amount) FROM Drawing d "
								+ "WHERE d.user = :user AND d.date > :date1")
				.setEntity("user", user).setTimestamp("date1", date).list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Double) {
			double sum = (Double) result.get(0);
			if (sum >= getTotal()) {
				gain(user);
			}
		}
	}

	protected abstract double getTotal();

}
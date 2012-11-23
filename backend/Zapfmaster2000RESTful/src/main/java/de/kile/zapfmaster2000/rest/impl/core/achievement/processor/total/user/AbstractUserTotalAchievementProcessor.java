package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public abstract class AbstractUserTotalAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();
		
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		List<?> result = session
				.createQuery(
						"SELECT SUM(d.amount) DROM Drawing d where d.user = :user")
				.setEntity("user", user).list();
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

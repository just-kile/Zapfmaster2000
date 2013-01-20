package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractUniqueAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public abstract class AbstractGlobalTotalAchievementProcessor extends
		AbstractUniqueAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("SELECT SUM(d.amount) FROM Drawing d");
		List<?> result = query.list();
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

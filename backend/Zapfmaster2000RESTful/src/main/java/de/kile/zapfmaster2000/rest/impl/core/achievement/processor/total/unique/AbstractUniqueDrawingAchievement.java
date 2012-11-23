package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.unique;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public abstract class AbstractUniqueDrawingAchievement extends
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
						"SELECT d FROM Drawing d "
								+ "WHERE d.user = :user ORDER BY d.date DESC")
				.setEntity("user", user).setMaxResults(1).list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Drawing) {
			Drawing drawing = (Drawing) result.get(0);
			if (drawing.getAmount() >= getAmount()) {
				gain(user);
			}
		}

	}

	protected abstract double getAmount();

}

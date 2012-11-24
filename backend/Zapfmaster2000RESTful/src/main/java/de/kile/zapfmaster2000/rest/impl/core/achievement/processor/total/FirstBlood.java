package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class FirstBlood extends AbstractAchievementProcessor {

	@Override
	public void process(Drawing pDrawing) {
		User user = pDrawing.getUser();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(pDrawing.getKeg());

		List<?> result = session
				.createQuery(
						"SELECT COUNT(*) FROM Drawing d WHERE d.keg = :keg")
				.setEntity("keg", pDrawing.getKeg()).list();
		tx.commit();
		
		int count = (Integer) result.get(0);
		if (count == 1) {
			gain(user);
		}
	}
}

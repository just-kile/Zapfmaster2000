package de.kile.zapfmaster2000.rest.impl.core.achievement.processor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public abstract class AbstractUniqueAchievementProcessor extends
		AbstractAchievementProcessor {

	@Override
	public boolean canGain(User pUser) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(getAccount());
		session.update(getAchievement());

		List<?> result = session
				.createQuery(
						"SELECT COUNT(*) FROM GainedAchievement a "
								+ "WHERE a.achievement = :achievement "
								+ "AND a.user.account = :account")
				.setEntity("achievement", getAchievement())
				.setEntity("account", getAccount()).list();
		tx.commit();
		long count = (Long) result.get(0);

		return count == 0;
	}
}

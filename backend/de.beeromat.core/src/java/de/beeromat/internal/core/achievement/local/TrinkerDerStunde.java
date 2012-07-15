package de.beeromat.internal.core.achievement.local;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.db.SessionFactoryUtil;
import de.beeromat.core.model.db.User;

public class TrinkerDerStunde extends AbstractAchievementProcessor {

	@Override
	public void process(User pUser) {
		final String query = "select d.userId, sum(d.realAmount) as drawSum from Drawing as d where d.date > :date1 group by d.userId order by drawSum desc";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Date date = calendar.getTime();

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();

		List<?> result = session.createQuery(query).setTimestamp("date1", date)
				.list();
		tx.commit();
		
		if (!result.isEmpty() && result.get(0) instanceof Object[]) {
			Object[] first = (Object[]) result.get(0);
			if (first.length == 2 && first[0] instanceof Integer) {
				Integer id = (Integer) first[0];
				if (id == pUser.getUserId()) {
					gain(pUser);
				}
			}
		}

	}

}

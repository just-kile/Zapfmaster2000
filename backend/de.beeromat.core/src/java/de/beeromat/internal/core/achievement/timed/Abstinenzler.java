package de.beeromat.internal.core.achievement.timed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.achievemnt.AbstractTimedAchievementProcessor;
import de.beeromat.core.model.db.User;
import de.beeromat.internal.core.draw.DrawManagerImpl;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

public class Abstinenzler extends AbstractTimedAchievementProcessor {

	@Override
	protected int getHour() {
		return 8;
	}

	@Override
	protected void doProcess() {
		final String query = "from User as u";

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		final Date now = calendar.getTime();

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<?> result = session.createQuery(query).list();
		tx.commit();

		for (Object rawResult : result) {
			if (rawResult instanceof User) {
				User user = (User) rawResult;
				if (user.getRfidTag() != DrawManagerImpl.GUEST_USER_RFID_TAG && canGain(user)) {
					final String query2 = "from Drawing as d where d.date > :date1 and d.userId = "
							+ user.getUserId();
					session = SessionFactoryUtil.getInstance()
							.getCurrentSession();
					tx = session.beginTransaction();
					List<?> result2 = session.createQuery(query2)
							.setTimestamp("date1", now).list();
					tx.commit();
					
					if (result2.isEmpty()) {
						gain(user);
					}


				}
			}
		}

	}

}

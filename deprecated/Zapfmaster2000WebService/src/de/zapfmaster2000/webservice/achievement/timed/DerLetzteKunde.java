package de.zapfmaster2000.webservice.achievement.timed;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.achievement.AbstractTimedAchievementProcessor;
import de.zapfmaster2000.webservice.db.DBUtil;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.Drawing;
import de.zapfmaster2000.webservice.model.db.User;

public class DerLetzteKunde extends AbstractTimedAchievementProcessor {

	@Override
	protected int getHour() {
		return 8;
	}

	@Override
	protected void doProcess() {
		final String query = "select d from Drawing as d where d.date > :date1 order by d.date DESC";

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<?> result = session.createQuery(query)
				.setTimestamp("date1", calendar.getTime()).list();
		tx.commit();

		if (!result.isEmpty()) {
			Object rawResult = result.get(0);
			if (rawResult instanceof Drawing) {
				Drawing drawing = (Drawing) rawResult;
				result = DBUtil
						.executeQuery("select u from User as u where u.userId = "
								+ drawing.getUserId());
				if (!result.isEmpty() && result.get(0) instanceof User) {
					User user = (User) result.get(0);
					if (canGain(user)) {
						gain(user);
					}
				}
			}
		}
	}

}

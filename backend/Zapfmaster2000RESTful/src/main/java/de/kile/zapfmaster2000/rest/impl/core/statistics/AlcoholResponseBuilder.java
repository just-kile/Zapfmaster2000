package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.AlcoholLevelResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class AlcoholResponseBuilder {

	/**
	 * Calculates the alcohol level of a given {@link User} taken the last
	 * 36 hours into account using the Widmark formula.
	 * 
	 * @param user
	 *            must be a valid user id.
	 * @param account
	 * @return alcohol level 0 if no user or no drawings were found.
	 */
	@SuppressWarnings("unchecked")
	public static AlcoholLevelResponse retrieveAlcoholLevelResponse(long user,
			Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -36);

		// Widmark formula c = A / (m*r)
		// c alcohol concentration in per mille
		// A mass of alcohol in g
		// => A = V * e* p
		// V = volume in ml
		// e = alcohol content = 0.048 TODO: brand specific
		// p = density of alcohol = .8
		// r reduction factor (M: .69, F: .57)
		// m mass of user

		List<Object[]> resultAmountByHour = session
				.createQuery(
						"SELECT (d.amount*1000*0.048*0.8), d.date, u.sex, u.weight"
								+ " FROM Drawing d, User u"
								+ " WHERE u.id = :user AND d.user = u AND u.account = :account"
								+ " AND d.date > :timestamp "
								+ " ORDER BY d.date ASC").setLong("user", user)
				.setEntity("account", account)
				.setDate("timestamp", calendar.getTime()).list();

		tx.commit();

		if (resultAmountByHour.size() > 0) {

			Sex sex = (Sex) resultAmountByHour.get(0)[2];

			int weight = (Integer) resultAmountByHour.get(0)[3];

			double reductionFactor;
			double alcoholBreakDown = 0.15; // per mille per hour
			if (sex == Sex.MALE) {
				reductionFactor = 1 / (weight * 0.69);
			} else {
				reductionFactor = 1 / (weight * 0.57);
			}

			Date oldDate = (Date) resultAmountByHour.get(0)[1];
			double oldConcentration = (Double) resultAmountByHour.get(0)[0]
					* reductionFactor;

			Date curDate;
			double curConcentration;

			for (int i = 1; i < resultAmountByHour.size(); i++) {
				curDate = (Date) resultAmountByHour.get(i)[1];
				curConcentration = (Double) resultAmountByHour.get(i)[0]
						* reductionFactor;

				oldConcentration = curConcentration + oldConcentration
						* (curDate.getTime() - oldDate.getTime()) / 1000 / 60
						/ 60 * alcoholBreakDown;
			}

			AlcoholLevelResponse response = new AlcoholLevelResponse();

			response.setAlcoholLevel(oldConcentration);

			return response;
		} else {
			AlcoholLevelResponse response = new AlcoholLevelResponse();
			response.setAlcoholLevel(0);
			return response;
		}
	}

}

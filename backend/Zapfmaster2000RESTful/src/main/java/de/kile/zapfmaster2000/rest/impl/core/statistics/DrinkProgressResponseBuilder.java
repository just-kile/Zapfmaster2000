package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.DrinkProgressResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class DrinkProgressResponseBuilder {

	/**
	 * Builds an {@link DrinkProgressResponse} for {@link User} or every user in
	 * the given time span with fixed interval 30 minutes.
	 * 
	 * @param account
	 * @param user
	 *            for all users use -1
	 * @param dFrom
	 *            can be <code>null</code> for all available drawings
	 * @param dTo
	 *            can be <code>null</code> for until now
	 * @return {@link DrinkProgressResponse} with <code>amount</code>
	 *         <code>{0.0}</code> if the given user does not exist or has no
	 *         drawings.
	 */
	public static DrinkProgressResponse retrieveDrinkResponse(Account account,
			long user, Date dFrom, Date dTo) {
		return retrieveDrinkResponse(account, user, dFrom, dTo, 30);
	}

	/**
	 * Builds an {@link DrinkProgressResponse} for {@link User} or every user in
	 * the given time span.
	 * 
	 * @param account
	 * @param user
	 *            for all users use -1
	 * @param dFrom
	 *            can be <code>null</code> for all available drawings
	 * @param dTo
	 *            can be <code>null</code> for until now
	 * @param interval
	 *            windows size in minutes for the progress plot.
	 * @return {@link DrinkProgressResponse} with <code>amount</code>
	 *         <code>{0.0}</code> if the given user does not exist or has no
	 *         drawings.
	 */
	@SuppressWarnings("unchecked")
	public static DrinkProgressResponse retrieveDrinkResponse(Account account,
			long user, Date dFrom, Date dTo, int interval) {

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		String sQuery = createQuery(dFrom, dTo, user);

		Query query = session.createQuery(sQuery);

		if (user != -1) {
			query.setLong("user", user);
		}
		if (dFrom != null) {
			query.setTimestamp("from", dFrom);
		}
		if (dTo != null) {
			query.setTimestamp("to", dTo);
		}

		query.setEntity("account", account);
		List<Object[]> list = query.list();
		tx.commit();

		// No drawings found
		if (list.size() == 0) {

			DrinkProgressResponse response = new DrinkProgressResponse();
			response.setAmount(new double[] { 0.0 });
			response.setInterval(interval);
			if (dFrom != null) {
				response.setFrom(dFrom);
			} else {
				response.setFrom(new Date());
			}
			return response;
		}

		// create amounts
		Date maxDate = (Date) list.get(0)[1];
		Date minDate = (Date) list.get(0)[1];
		for (Object[] rawResponse : list) {
			if (((Date) rawResponse[1]).after(maxDate))
				maxDate = (Date) rawResponse[1];
			if (((Date) rawResponse[1]).before(minDate))
				minDate = (Date) rawResponse[1];
		}

		if (dTo != null)
			maxDate = dTo;
		if (dFrom != null)
			minDate = dFrom;
		else
			dFrom = minDate;

		double[] amounts = new double[(int) Math.ceil(Double.valueOf(maxDate
				.getTime() - minDate.getTime())
				/ 1000 / 60 / interval)];

		if (amounts.length == 0){//only one drawing
			DrinkProgressResponse response = new DrinkProgressResponse();
			amounts = new double [1];
			amounts[0] = (Double) list.get(0)[0];
			
			response.setFrom(dFrom);
			response.setInterval(interval);
			
			return response;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(minDate);
		cal.add(Calendar.MINUTE, interval);

		Calendar curDate = Calendar.getInstance();
		int idx = 0;

		for (Object[] rawAmountDate : list) {
			curDate.setTime((Date) rawAmountDate[1]);

			while (cal.before(curDate)) {
				cal.add(Calendar.MINUTE, interval);
				idx++;
			}
			amounts[idx] += (Double) rawAmountDate[0];
		}

		DrinkProgressResponse response = new DrinkProgressResponse();
		response.setAmount(amounts);
		response.setFrom(dFrom);
		response.setInterval(interval);

		return response;
	}

	private static String createQuery(Date dFrom, Date dTo, long user) {
		// TODO change to criteria
		String sQuery = "SELECT d.amount, d.date" + " FROM Drawing d, User u"
				+ " WHERE u.account = :account AND d.user = u"
				+ " %USER% %FROM% %TO% ORDER BY d.date";

		if (user != -1)
			sQuery = sQuery.replaceAll("%USER%", " AND u.id = :user");
		else
			sQuery = sQuery.replaceAll("%USER%", "");

		if (dFrom != null)
			sQuery = sQuery.replaceAll("%FROM%", " AND d.date >= :from");
		else
			sQuery = sQuery.replaceAll("%FROM%", "");

		if (dTo != null)
			sQuery = sQuery.replaceAll("%TO%", " AND d.date <= :to");
		else
			sQuery = sQuery.replaceAll("%TO%", "");

		return sQuery;
	}

}

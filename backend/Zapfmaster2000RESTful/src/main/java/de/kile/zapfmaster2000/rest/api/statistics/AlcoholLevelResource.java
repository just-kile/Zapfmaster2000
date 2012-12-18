package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;

@Path("statistics")
public class AlcoholLevelResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	// TODO add user independent

	/**
	 * Returns the {@link AlcoholLevelResponse} calculated by the Widmark
	 * formula with alcohol break down of 0.15 per mille per hour.
	 * 
	 * @param pToken
	 * @param pUser
	 * @return either {@link AlcoholLevelResponse} or <code>null</code> if
	 *         <code>pUser</code> does not exist or has not drawn anything.
	 */
	@Path("alcoholLevel")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAlcoholLevel(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			AlcoholLevelResponse response = null;
			response = createAlcoholLevelResponse(pUser, account);

			if (response != null)
				return Response.ok(response).build();
			else {
				LOG.error("No drawings found");
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Status.FORBIDDEN).build();
	}

	@SuppressWarnings("unchecked")
	public AlcoholLevelResponse createAlcoholLevelResponse(String pUser,
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
								+ " ORDER BY d.date ASC")
				.setLong("user", Long.valueOf(pUser))
				.setEntity("account", account)
				.setDate("timestamp", calendar.getTime()).list();

		tx.commit();

		if (resultAmountByHour.size() > 0) {// else FORBIDDEN

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
		} else
			return null;
	}
}

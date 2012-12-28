package de.kile.zapfmaster2000.rest.api.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.util.ChallengeAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

@Path("/challenge")
public class ChallengeResource {

	private static final Logger LOG = Logger.getLogger(ChallengeResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveOverview(@QueryParam("token") String pToken) {
		return retrieveOverview(pToken, false);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveOverview(@QueryParam("token") String pToken,
			@QueryParam("onlyRunning") boolean pOnlyRunning) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			String query = "FROM Challenge1v1 c "
					+ "JOIN FETCH c.user1 JOIN FETCH c.user2 "
					+ "WHERE c.user1.account.id = :accountId ";
			if (pOnlyRunning) {
				query += "AND c.state = RUNNING";
			} else {
				query += "AND (c.state = RUNNING OR c.state = DONE)";
			}

			@SuppressWarnings("unchecked")
			List<Challenge1v1> result = session.createQuery(query)
					.setLong("accountId", account.getId()).list();
			tx.commit();

			List<ChallengeOverviewReponse> response = new ArrayList<>();
			ChallengeAdapter adapter = new ChallengeAdapter();
			for (Challenge1v1 c : result) {
				response.add(adapter.adaptChallenge(c));
			}
			return Response.ok(response).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	/**
	 * Retrieve the users that may be challenged. This is the list of currently
	 * logged in users.
	 * 
	 * @param pToken
	 *            the token to retrieve the users for.
	 */
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUsers(@QueryParam("token") String pToken) {
		User user = Zapfmaster2000Core.INSTANCE.getAuthService().retrieveUser(
				pToken);
		if (user != null) {
			List<User> loggedInUsers = Zapfmaster2000Core.INSTANCE
					.getChallengeService().retrieveLoggedInUsers(
							user.getAccount());
			List<LoggedInUserReponse> response = new ArrayList<>();
			for (User loggedInUser : loggedInUsers) {
				// ignore user that requested the user list
				if (loggedInUser.getId() != user.getId()) {
					LoggedInUserReponse r = new LoggedInUserReponse();
					r.setUserId(loggedInUser.getId());
					r.setUserName(loggedInUser.getName());
					r.setUserImage(loggedInUser.getImagePath());
					response.add(r);
				}
			}

			// order by user name
			Collections.sort(response, new Comparator<LoggedInUserReponse>() {
				@Override
				public int compare(LoggedInUserReponse o1,
						LoggedInUserReponse o2) {
					return o1.getUserName().compareTo(o2.getUserName());
				}
			});

			// return list
			return Response.ok(response).build();
		}
		return Response.status(Status.FORBIDDEN).build();

	}

	/**
	 * Will start a new pending challenge.
	 * 
	 * @param pRequest
	 *            input
	 * @return
	 */
	@POST
	@Path("/start/1v1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response startChallenge(
			@FormParam("challengeeId") long pChallengeeId,
			@FormParam("duration") int pDuration,
			@FormParam("token") String pToken) {
		User challenger = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveUser(pToken);

		if (challenger != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			EClass userClass = Zapfmaster2000Package.eINSTANCE.getUser();
			User user1 = (User) session.get(userClass.getName(),
					challenger.getId());
			User user2 = (User) session.get(userClass.getName(), pChallengeeId);
			if (user2 == null
					|| user1.getAccount().getId() != user2.getAccount().getId()) {
				LOG.warn("Invalid challengee id: " + pChallengeeId);
				return Response.status(Status.BAD_REQUEST).build();
			}
			tx.commit();

			Challenge1v1 challenge = Zapfmaster2000Core.INSTANCE
					.getChallengeService().createPendingChallenge1v1(user1,
							user2, pDuration);

			return Response.ok(challenge.getId()).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	@POST
	@Path("/accept")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response acceptChallenge(@FormParam("token") String pToken,
			@FormParam("pendingChallengeId") long pChallengeId) {

		User user = Zapfmaster2000Core.INSTANCE.getAuthService().retrieveUser(
				pToken);

		if (user != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			EClass challenge1v1Class = Zapfmaster2000Package.eINSTANCE
					.getChallenge1v1();
			Challenge1v1 challenge1v1 = (Challenge1v1) session.get(
					challenge1v1Class.getName(), pChallengeId);
			if (challenge1v1 == null
					|| challenge1v1.getUser2().getId() != user.getId()) {
				LOG.warn("User " + user.getId() + " cannot accept challenge "
						+ pChallengeId);
				return Response.status(Status.BAD_REQUEST).build();
			}

			tx.commit();

			Zapfmaster2000Core.INSTANCE.getChallengeService().startChallenge(
					challenge1v1);

			return Response.ok().build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	@POST
	@Path("/decline")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response declineChallenge(@FormParam("token") String pToken,
			@FormParam("pendingChallengeId") long pChallengeId) {

		User user = Zapfmaster2000Core.INSTANCE.getAuthService().retrieveUser(
				pToken);

		if (user != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			EClass challenge1v1Class = Zapfmaster2000Package.eINSTANCE
					.getChallenge1v1();
			Challenge1v1 challenge1v1 = (Challenge1v1) session.get(
					challenge1v1Class.getName(), pChallengeId);
			if (challenge1v1 == null
					|| challenge1v1.getUser2().getId() != user.getId()) {
				LOG.warn("User " + user.getId() + " cannot accept challenge "
						+ pChallengeId);
				return Response.status(Status.BAD_REQUEST).build();
			}

			Zapfmaster2000Core.INSTANCE.getChallengeService().declineChallenge(
					challenge1v1);

			tx.commit();

			return Response.ok().build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
}

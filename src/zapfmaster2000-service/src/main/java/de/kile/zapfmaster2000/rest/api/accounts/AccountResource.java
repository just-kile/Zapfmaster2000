package de.kile.zapfmaster2000.rest.api.accounts;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

@Path("/account")
public class AccountResource {

	private static final Logger LOG = Logger.getLogger(AccountResponse.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retriveAvailableAccounts() {

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Account> result = session.createQuery("From Account a").list();
		tx.commit();

		List<AccountResponse> accounts = new ArrayList<>();
		for (Account account : result) {
			AccountResponse response = new AccountResponse();
			response.setAccountId(account.getId());
			response.setName(account.getName());
			accounts.add(response);
		}

		return Response.ok(accounts).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAccount(@FormParam("name") String pName) {

		LOG.info("Creating Account with name " + pName);
		// check that the given account name is a valid string
		if (pName != null && !pName.equals("")) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
			account.setName(pName);
			session.save(account);
			tx.commit();

			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteAccount(@FormParam("token") String pToken,
			@FormParam("accountId") long accountId) {

		LOG.info("Deleting Account with id " + accountId);

		// check that the given account name is a valid string
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Account deletionAccount = (Account) session
					.createQuery("From Account a WHERE a.id = :accountId")
					.setLong("accountId", accountId).uniqueResult();
			session.delete(deletionAccount);
			tx.commit();

			return Response.ok().build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
}

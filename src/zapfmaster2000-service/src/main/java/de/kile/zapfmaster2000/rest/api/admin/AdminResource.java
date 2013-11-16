package de.kile.zapfmaster2000.rest.api.admin;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.emf.ecore.EClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

@Path("/admin")
public class AdminResource {

	@POST
	@Path("/global/create")
	public Response createGlobalAdmin(String adminName, String password,
			String token) {

		Admin admin = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAdmin(token);
		if (admin != null && admin.getAccount() == null) {
			createAdmin(adminName, password, null);
			return Response.ok().build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	@POST
	@Path("/account/{accountId}/create")
	public Response createAccountAdmin(@QueryParam("accountId") long accountId,
			String adminName, String password, String token) {

		Admin admin = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAdmin(token);
		if (admin != null
				&& (admin.getAccount() == null || admin.getAccount().getId() == accountId)) {

			Account account = retiveAccount(accountId);

			if (account == null) {
				return Response.status(Status.FORBIDDEN).build();
			} else {
				createAdmin(adminName, password, account);
				return Response.ok().build();
			}

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	private Account retiveAccount(long accountId) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		EClass accountClass = Zapfmaster2000Package.eINSTANCE.getAccount();
		Account account = (Account) session.get(accountClass.getName(),
				accountId);

		tx.commit();
		return account;
	}

	private void createAdmin(String adminName, String password, Account account) {
		Admin admin = Zapfmaster2000Factory.eINSTANCE.createAdmin();
		admin.setName(adminName);
		admin.setPassword(password);
		admin.setAccount(account);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(admin);

		tx.commit();
	}
}

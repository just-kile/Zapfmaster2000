package de.kile.zapfmaster2000.rest.api.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.emf.ecore.EClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.admin.AdminResponse.Type;
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
				&& (isGlobalAdmin(admin) || admin.getAccount().getId() == accountId)) {

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

	@GET
	@Path("/loginstatus")
	public Response checkLoginStatus(@QueryParam("token") String token) {
		Admin admin = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAdmin(token);

		if (admin != null) {
			AdminInfo status = new AdminInfo();
			status.setAdminName(admin.getName());
			if (isGlobalAdmin(admin)) {
				status.setType(Type.global);
			} else {
				status.setType(Type.account);
			}

			return Response.ok(status).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	@GET
	@Path("/account")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAccountAdmins(@QueryParam("token") String token) {
		Admin admin = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAdmin(token);

		if (isGlobalAdmin(admin)) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Admin> result = session.createQuery("FROM Admin").list();
			List<AdminResponse> responseList = new ArrayList<>();

			for (Admin retrievedAdmin : result) {

				AdminResponse response = new AdminResponse();
				response.setAdminId(retrievedAdmin.getId());
				response.setAdminName(retrievedAdmin.getName());

				if (isGlobalAdmin(retrievedAdmin)) {
					response.setType(Type.global);
				} else {
					Account account = retrievedAdmin.getAccount();
					response.setAccountName(account.getName());
					response.setAccountId(account.getId());
					response.setType(Type.account);
				}

				responseList.add(response);

			}

			tx.commit();

			return Response.ok(responseList).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	private boolean isGlobalAdmin(Admin admin) {
		return admin != null && admin.getAccount() == null;
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

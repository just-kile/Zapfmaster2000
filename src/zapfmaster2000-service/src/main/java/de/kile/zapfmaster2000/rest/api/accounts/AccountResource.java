package de.kile.zapfmaster2000.rest.api.accounts;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;

@Path("/account")
public class AccountResource {

	private static final Logger LOG = Logger.getLogger(AccountResponse.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retriveAvailableAccounts() {

		//Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
		//		.retrieveAccount(pToken);

		//if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Account> result = session
					.createQuery("From Account a")
					.list();
			tx.commit();

			List<AccountResponse> accounts = new ArrayList<>();
			for (Account account : result) {
                AccountResponse response = new AccountResponse();
				response.setAccountId(account.getId());
				response.setName(account.getName());
                accounts.add(response);
			}

			return Response.ok(accounts).build();
		//} else {
		//	return Response.status(Status.FORBIDDEN).build();
		//}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAccount(@FormParam("name") String pName) {

		LOG.info("Creating Account with name " + pName);
    	// check that the given account name is a valid string
        if(pName !=null && !pName.equals("")){


			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
            Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
            account.setName(pName);
            session.save(account);
            session.getTransaction().commit();


            return Response.ok().build();
        }else{
            return Response.status(Status.BAD_REQUEST).build();
        }
	}
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteAccount(
            @FormParam("token") String pToken,
            @FormParam("accountId") long accountId) {

        LOG.info("Deleting Account with id " + accountId);

        // check that the given account name is a valid string
        Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
                .retrieveAccount(pToken);

        if(account!=null){

            Session session = Zapfmaster2000Core.INSTANCE
                    .getTransactionService().getSessionFactory()
                    .getCurrentSession();
            Transaction tx = session.beginTransaction();
            Account deletionAccount =(Account) session
                    .createQuery("From Account a WHERE a.id = :accountId")
                    .setLong("accountId", accountId).uniqueResult();
            tx.commit();
            session.delete(deletionAccount);
            session.getTransaction().commit();
            return Response.ok().build();
        }else{
            return Response.status(Status.FORBIDDEN).build();
        }
    }
}

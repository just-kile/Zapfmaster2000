package de.kile.zapfmaster2000.rest.api.members;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/members")
public class MembersResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveMembers(@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			List<?> result = session
					.createQuery(
							"SELECT u.name, u.imagePath, u.id FROM User u "
									+ "WHERE u.account.id = :accountId")
					.setLong("accountId", account.getId()).list();

			List<MemberResponse> response = new ArrayList<>();
			for (Object rawResult : result) {
				Object[] resultArray = (Object[]) rawResult;

				MemberResponse memberResponse = new MemberResponse();
				memberResponse.setUserId((Long) resultArray[2]);
				memberResponse.setUserName((String) resultArray[0]);
				memberResponse.setImagePath((String) resultArray[1]);

				response.add(memberResponse);
			}

			tx.commit();
			return Response.ok(response).build();
		}
		return Response.status(Status.FORBIDDEN).build();

	}

}

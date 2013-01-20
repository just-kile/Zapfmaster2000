package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.UserResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class UserResponseBuilder {

	@SuppressWarnings("unchecked")
	public static UserResponse retrieveUserResponse(long user, Account account) {

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Object> resp = session
				.createQuery("SELECT u FROM User u WHERE u.id = :id AND u.account = :account")
				.setEntity("account", account).setLong("id", user).list();
		tx.commit();

		User retrievedUser = (User) resp.get(0);

		UserResponse result = new UserResponse();

		result.setUserId(user);
		result.setUserImage(retrievedUser.getImagePath());
		result.setUserName(retrievedUser.getName());

		return result;
	}

}

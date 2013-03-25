package de.kile.zapfmaster2000.rest.impl.core.statistics;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.members.MemberResponse;
import de.kile.zapfmaster2000.rest.api.members.MemberResponse.GainedUserAchievement;
import de.kile.zapfmaster2000.rest.api.statistics.KegResponse;
import de.kile.zapfmaster2000.rest.api.statistics.UserResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class UserBuilder {

	/**
	 * Builds {@link UserResponse}.
	 * 
	 * @param account
	 * @param pToken
	 * @param pUser
	 * @param user
	 * @return {@link UserResponse}
	 */
	@SuppressWarnings("unchecked")
	public static UserResponse retrieveUserResponse(Account account,String pToken,String pUser,User user) {
		UserResponse userResponse = new UserResponse();
		if(pUser==null){
			userResponse.setUserId(user.getId());
			userResponse.setUserName(user.getName());
			userResponse.setUserImage(user.getImagePath());
			
		}else{
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			List<?> result = session
					.createQuery(
							"SELECT name, imagePath, id "
									+ "FROM User "
									+ "WHERE account.id = :accountId AND id = :userId")
					.setLong("accountId", account.getId()).setString("userId", pUser).list();

			Object rawResult = result.get(0);
			Object[] resultArray = (Object[]) rawResult;
			userResponse.setUserId((Long) resultArray[2]);
			userResponse.setUserName((String) resultArray[0]);
			userResponse.setUserImage((String) resultArray[1]);

			tx.commit();
		}
		
		
		return userResponse;
	}
}

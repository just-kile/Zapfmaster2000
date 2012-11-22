package de.kile.zapfmaster2000.rest.impl.core.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.constants.HttpSessionConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class AuthServiceImpl implements AuthService {

	@Override
	public Account loginAccount(String pAccountName, HttpServletRequest pRequest) {
		Account account = null;
		if (pAccountName != null && pRequest != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery(
					"SELECT a FROM Account a WHERE a.name = :name").setString(
					"name", pAccountName);

			@SuppressWarnings("unchecked")
			List<Account> results = query.list();

			if (results.size() == 1) {
				// login succeeded
				HttpSession httpSession = pRequest.getSession();
				account = results.get(0);
				httpSession.setAttribute(HttpSessionConstants.ACCOUNT, account);
			}
			tx.commit();
		}
		return account;
	}

	@Override
	public Account retrieveAccount(HttpServletRequest pRequest) {
		if (pRequest != null && pRequest.getSession(false) != null) {
			Object rawObject = pRequest.getSession().getAttribute(
					HttpSessionConstants.ACCOUNT);
			if (rawObject instanceof Account) {
				return (Account) rawObject;
			}
		}
		return null;
	}

}

package de.kile.zapfmaster2000.rest.core.auth;

import javax.servlet.http.HttpServletRequest;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

/**
 * Service to deal with authentications.
 * 
 * @author Thomas Kipar
 */
public interface AuthService {

	/**
	 * Login via a Zapfmaster2000 Account.
	 * 
	 * <p>
	 * Will create a session if authentication succeeds.
	 * </p>
	 * 
	 * @param pAccountName
	 *            account name
	 * @param pRequest
	 *            the http request to perform the login for (and create the
	 *            session for).
	 * @return the account if authenticated succeeds, <code>null</code>
	 *         otherwise.
	 */
	public Account loginAccount(String pAccountName, HttpServletRequest pRequest);

	/**
	 * Retrieves the account associated with the session for a given request. If
	 * no account is attached to the session (or the session does not exist at
	 * all), this method will return <code>null</code>.
	 * 
	 * @param pRequest
	 *            the request to get the account for
	 * @return account or <code>null</code> if no log in was done before.
	 */
	public Account retrieveAccount(HttpServletRequest pRequest);
}

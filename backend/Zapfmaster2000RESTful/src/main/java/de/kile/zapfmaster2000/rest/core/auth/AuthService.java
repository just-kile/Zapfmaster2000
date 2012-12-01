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
	 * Will return the token if the login succeeds.
	 * </p>
	 * 
	 * @param pAccountName
	 *            account name
	 * @return the token or <code>null</code> if the authentication did not
	 *         succeed.
	 */
	public String loginAccount(String pAccountName);

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

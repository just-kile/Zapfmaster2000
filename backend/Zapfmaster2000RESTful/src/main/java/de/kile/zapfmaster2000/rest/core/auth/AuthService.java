package de.kile.zapfmaster2000.rest.core.auth;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

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
	 * Login via user account.
	 * 
	 * <p>
	 * Will return the token if the login succeeds.
	 * </p>
	 * 
	 * @param pUserName
	 *            user name
	 * @param pPassword
	 *            password
	 * @return the token or <code>null</code> if the authentication did not
	 *         succeed.
	 */
	public String loginUser(String pUserName, String pPassword);

	/**
	 * Retrieves the account associated with the session for a given token. If
	 * no account is attached to the token (or the token does not exist at all),
	 * this method will return <code>null</code>.
	 * 
	 * @param pToken
	 *            the token to get the account for
	 * @return account or <code>null</code> if no log in was done before.
	 */
	public Account retrieveAccount(String pToken);

	/**
	 * Retrieves the use associated with the session for a given token. If no
	 * user is attached to the token (or the token does not exist at all), this
	 * method will return <code>null</code>.
	 * 
	 * @param pToken
	 *            the token to get the account for
	 * @return account or <code>null</code> if no log in was done before.
	 */
	public User retrieveUser(String pToken);

}

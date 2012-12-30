package de.kile.zapfmaster2000.rest.core.registration;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public interface RegistrationService {

	/**
	 * 
	 * 
	 * @param pAccount
	 * @param pUserName
	 * @param pPassword
	 * @param pSex
	 * @param pWeight
	 * 
	 * @return the new user, never <code>null</code>.
	 * @throws RegistrationException
	 *             if the registration did not succeed
	 */
	public User registerUser(Account pAccount, String pUserName,
			String pPassword, Sex pSex, int pWeight)
			throws RegistrationException;
	
	public void addListener(RegistrationServiceListener pListener);
	
	public void removeListener(RegistrationServiceListener pListener);

}

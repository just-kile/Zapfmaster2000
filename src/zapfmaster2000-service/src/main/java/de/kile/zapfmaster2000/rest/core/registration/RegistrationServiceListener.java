package de.kile.zapfmaster2000.rest.core.registration;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public interface RegistrationServiceListener {

	public void onNewUserRegistered(User pUser);
	
}

package de.kile.zapfmaster2000.rest.core.auth;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;

public class AuthUtil {

	public static Account retrieveAccount(String pToken) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account == null) {
			Admin admin = Zapfmaster2000Core.INSTANCE.getAuthService()
					.retrieveAdmin(pToken);
			if (isAccountAdmin(admin)) {
				account = admin.getAccount();
			}
		}
		return account;
	}

	public static boolean isAccountAdmin(Admin admin) {
		return admin != null && admin.getAccount() != null;
	}
}

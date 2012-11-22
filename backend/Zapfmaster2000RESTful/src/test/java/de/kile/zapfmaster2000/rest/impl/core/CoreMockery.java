package de.kile.zapfmaster2000.rest.impl.core;

import org.junit.After;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;

/**
 * Utility class to mock managers from the zapfmaster 2000 core.
 * 
 * @author Thomas Kipar
 */
public class CoreMockery {

	private TransactionService originalTransactionService;

	private ConfigurationService originalConfigurationServoce;

	private BoxService originalBoxService;
	
	private AuthService originalAuthService;

	public void mockTransactionService(TransactionService pManager) {
		originalTransactionService = getCore().getTransactionService();
		getCore().setTransactionManager(pManager);
	}

	public void mockConfigurationService(ConfigurationService pManager) {
		originalConfigurationServoce = getCore().getConfigurationService();
		getCore().setConfigurationManager(pManager);
	}

	public void mockBoxService(BoxService pManager) {
		originalBoxService = getCore().getBoxService();
		getCore().setBoxManager(pManager);
	}
	
	public void mockAuthService(AuthService pAuthService) {
		originalAuthService = getCore().getAuthService();
		getCore().setAuthService(originalAuthService);
	}

	@After
	public void resetMocks() {
		if (originalTransactionService != null) {
			getCore().setTransactionManager(originalTransactionService);
		}
		if (originalConfigurationServoce != null) {
			getCore().setConfigurationManager(originalConfigurationServoce);
		}
		if (originalBoxService != null) {
			getCore().setBoxManager(originalBoxService);
		}
	}

	private Zapfmaster2000CoreImpl getCore() {
		return (Zapfmaster2000CoreImpl) Zapfmaster2000Core.INSTANCE;
	}
}

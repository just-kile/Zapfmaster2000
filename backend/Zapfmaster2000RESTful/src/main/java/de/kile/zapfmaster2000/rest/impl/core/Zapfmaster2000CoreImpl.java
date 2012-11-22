package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
import de.kile.zapfmaster2000.rest.impl.core.auth.AuthServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.box.BoxServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.configuration.FileConfiguratioServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.transaction.TransactionServiceImpl;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;

	/** the transaction manager */
	private TransactionService transactionManager;

	/** the configuration manager */
	private ConfigurationService configurationManager;

	/** the box manager */
	private BoxService boxManager;

	/** the auth service */
	private AuthService authService;

	private Zapfmaster2000CoreImpl() {
		transactionManager = new TransactionServiceImpl();
		configurationManager = new FileConfiguratioServiceImpl();
		boxManager = new BoxServiceImpl();
		authService = new AuthServiceImpl();
	}

	/**
	 * Creates / provides the singleton instance of the
	 * {@link Zapfmaster2000Core}.
	 * 
	 * @return the core. Never <code>null</code>.
	 */
	public synchronized static Zapfmaster2000Core init() {
		if (instance == null) {
			instance = new Zapfmaster2000CoreImpl();
		}
		return instance;
	}

	@Override
	public TransactionService getTransactionService() {
		return transactionManager;
	}

	@Override
	public BoxService getBoxService() {
		return boxManager;
	}

	@Override
	public ConfigurationService getConfigurationService() {
		return configurationManager;
	}

	@Override
	public AuthService getAuthService() {
		return authService;
	}

	/**
	 * Sets a transaction service. Use only for mocking in unit tests!
	 * 
	 * @param pTransactionManager
	 *            the service to set
	 */
	void setTransactionManager(TransactionService pTransactionManager) {
		transactionManager = pTransactionManager;
	}

	/**
	 * Sets the configuration service. Use only for mocking in unit tests!
	 * 
	 * @param pConfigurationManager
	 *            the service to set
	 */
	void setConfigurationManager(ConfigurationService pConfigurationManager) {
		configurationManager = pConfigurationManager;
	}

	/**
	 * Sets the box service. Use only for mocking in unit tests!
	 * 
	 * @param pBoxManager
	 *            the service to set
	 */
	void setBoxManager(BoxService pBoxManager) {
		boxManager = pBoxManager;
	}

	/**
	 * Sets the auth service. Use only for mocking in unit tests!
	 * 
	 * @param pAuthService
	 *            the service to set
	 */
	void setAuthService(AuthService pAuthService) {
		authService = pAuthService;
	}

}

package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.TransactionService;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.impl.core.box.BoxServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.configuration.FileConfiguratioServiceImpl;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;

	/** the transaction manager */
	private TransactionService transactionManager;

	/** the configuration manager */
	private ConfigurationService configurationManager;

	/** the box manager */
	private BoxService boxManager;

	private Zapfmaster2000CoreImpl() {
		transactionManager = new TransactionServiceImpl();
		configurationManager = new FileConfiguratioServiceImpl();
		boxManager = new BoxServiceImpl();
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

	/**
	 * Sets a transaction manager. Use only for mocking in unit tests!
	 * 
	 * @param pTransactionManager
	 *            the manager to set
	 */
	void setTransactionManager(TransactionService pTransactionManager) {
		transactionManager = pTransactionManager;
	}

	/**
	 * Sets the configuration manager. Use only for mocking in unit tests!
	 * 
	 * @param pConfigurationManager
	 *            the manager to set
	 */
	void setConfigurationManager(ConfigurationService pConfigurationManager) {
		configurationManager = pConfigurationManager;
	}

	/**
	 * Sets the box manager. Use only for mocking in unit tests!
	 * 
	 * @param pBoxManager
	 *            the manager to set
	 */
	void setBoxManager(BoxService pBoxManager) {
		boxManager = pBoxManager;
	}

}

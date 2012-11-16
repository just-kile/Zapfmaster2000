package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxManager;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationManager;
import de.kile.zapfmaster2000.rest.impl.core.box.BoxManagerImpl;
import de.kile.zapfmaster2000.rest.impl.core.configuration.FileConfigurationManagerImpl;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;

	/** the transaction manager */
	private TransactionManager transactionManager;

	/** the configuration manager */
	private ConfigurationManager configurationManager;

	/** the box manager */
	private BoxManager boxManager;

	private Zapfmaster2000CoreImpl() {
		transactionManager = new TransactionManagerImpl();
		configurationManager = new FileConfigurationManagerImpl();
		boxManager = new BoxManagerImpl();
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
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	@Override
	public BoxManager getBoxManager() {
		return boxManager;
	}

	@Override
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	/**
	 * Sets a transaction manager. Use only for mocking in unit tests!
	 * 
	 * @param pTransactionManager
	 *            the manager to set
	 */
	void setTransactionManager(TransactionManager pTransactionManager) {
		transactionManager = pTransactionManager;
	}

	/**
	 * Sets the configuration manager. Use only for mocking in unit tests!
	 * 
	 * @param pConfigurationManager
	 *            the manager to set
	 */
	void setConfigurationManager(ConfigurationManager pConfigurationManager) {
		configurationManager = pConfigurationManager;
	}

	/**
	 * Sets the box manager. Use only for mocking in unit tests!
	 * 
	 * @param pBoxManager
	 *            the manager to set
	 */
	void setBoxManager(BoxManager pBoxManager) {
		boxManager = pBoxManager;
	}

}

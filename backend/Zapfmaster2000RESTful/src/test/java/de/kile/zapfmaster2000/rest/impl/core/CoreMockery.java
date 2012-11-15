package de.kile.zapfmaster2000.rest.impl.core;

import org.junit.After;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxManager;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationManager;

/**
 * Utility class to mock managers from the zapfmaster 2000 core.
 * 
 * @author Thomas Kipar
 */
public class CoreMockery {

	private TransactionManager originalTransactionManager;

	private ConfigurationManager originalConfigurationManager;

	private BoxManager originalBoxManager;

	public void mockTransactionManager(TransactionManager pManager) {
		originalTransactionManager = getCore().getTransactionManager();
		getCore().setTransactionManager(pManager);
	}

	public void mockConfigurationManager(ConfigurationManager pManager) {
		originalConfigurationManager = getCore().getConfigurationManager();
		getCore().setConfigurationManager(pManager);
	}

	public void mockBoxManager(BoxManager pManager) {
		originalBoxManager = getCore().getBoxManager();
		getCore().setBoxManager(pManager);
	}

	@After
	public void resetMocks() {
		if (originalTransactionManager != null) {
			getCore().setTransactionManager(originalTransactionManager);
		}
		if (originalConfigurationManager != null) {
			getCore().setConfigurationManager(originalConfigurationManager);
		}
		if (originalBoxManager != null) {
			getCore().setBoxManager(originalBoxManager);
		}
	}

	private Zapfmaster2000CoreImpl getCore() {
		return (Zapfmaster2000CoreImpl) Zapfmaster2000Core.INSTANCE;
	}
}

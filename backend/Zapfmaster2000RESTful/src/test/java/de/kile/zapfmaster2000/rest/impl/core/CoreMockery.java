package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.Zapfmaster2000CoreImpl;

/**
 * Utility class to mock managers from the zapfmaster 2000 core.
 * 
 * @author Thomas Kipar
 */
public class CoreMockery {

	private TransactionManager originalTransactionManager;

	public void mockTransactionManager(TransactionManager pManager) {
		originalTransactionManager = getCore().getTransactionManager();
		getCore().setTransactionManager(pManager);
	}
	
	public void resetMocks() {
		if (originalTransactionManager != null) {
			getCore().setTransactionManager(originalTransactionManager);
		}
	}

	private Zapfmaster2000CoreImpl getCore() {
		return (Zapfmaster2000CoreImpl) Zapfmaster2000Core.INSTANCE;
	}
}

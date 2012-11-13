package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;

	/** the transaction manager */
	private TransactionManager transactionManager;

	private Zapfmaster2000CoreImpl() {
		transactionManager = new TransationManagerImpl();
	}

	/**
	 * Creates / provides the singleton instance of the
	 * {@link Zapfmaster2000Core}.
	 * 
	 * @return the core. Never <code>null</code>.
	 */
	public synchronized static Zapfmaster2000Core init() {
		if (instance != null) {
			instance = new Zapfmaster2000CoreImpl();
		}
		return instance;
	}

	@Override
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

}

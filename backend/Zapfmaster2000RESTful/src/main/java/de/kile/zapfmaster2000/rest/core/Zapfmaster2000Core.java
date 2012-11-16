package de.kile.zapfmaster2000.rest.core;

import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.impl.core.Zapfmaster2000CoreImpl;

/**
 * The core is the central unit to provide zapfmaster functionality for the rest
 * service.
 * 
 * <p>
 * The core is a singleton.
 * </p>
 * 
 * @author Thomas Kipar
 */
public interface Zapfmaster2000Core {

	/** shared instance */
	public static Zapfmaster2000Core INSTANCE = Zapfmaster2000CoreImpl.init();

	/**
	 * Returns the transaction manager.
	 * 
	 * @return the transation manager, never <code>null</code>.
	 */
	public TransactionService getTransactionService();

	/**
	 * Returns the box manager.
	 * 
	 * @return the box manager, never <code>null</code>.
	 */
	public BoxService getBoxService();
	
	
	/**
	 * Returns the configuration manager.
	 * 
	 * @return the configuration manager, never <code>null</code>.
	 */
	public ConfigurationService getConfigurationService();

}

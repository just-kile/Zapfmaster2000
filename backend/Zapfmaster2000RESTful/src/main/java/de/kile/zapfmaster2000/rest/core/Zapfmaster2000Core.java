package de.kile.zapfmaster2000.rest.core;

import de.kile.zapfmaster2000.rest.core.box.BoxManager;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationManager;
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
	public TransactionManager getTransactionManager();

	/**
	 * Returns the box manager.
	 * 
	 * @return the box manager, never <code>null</code>.
	 */
	public BoxManager getBoxManager();
	
	
	/**
	 * Returns the configuration manager.
	 * 
	 * @return the configuration manager, never <code>null</code>.
	 */
	public ConfigurationManager getConfigurationManager();

}

package de.kile.zapfmaster2000.rest.core;

import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
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
	 * Returns the transaction service.
	 * 
	 * @return the transaction service, never <code>null</code>.
	 */
	public TransactionService getTransactionService();

	/**
	 * Returns the box service.
	 * 
	 * @return the box service, never <code>null</code>.
	 */
	public BoxService getBoxService();
	
	
	/**
	 * Returns the configuration service.
	 * 
	 * @return the configuration service, never <code>null</code>.
	 */
	public ConfigurationService getConfigurationService();
	
	/**
	 * Returns the auth service.
	 * 
	 * @return the auth service, never <code>null</code>.
	 */
	public AuthService getAuthService();
	
	/**
	 * Returns the achievement service.
	 * 
	 * @return the achievement service,never <code>null</code>.
	 */
	public AchievementService getAchievementService();

}

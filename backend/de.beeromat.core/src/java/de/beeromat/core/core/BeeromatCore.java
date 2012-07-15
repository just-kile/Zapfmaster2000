package de.beeromat.core.core;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.input.RawInputProvider;
import de.beeromat.core.keg.KegManager;
import de.beeromat.core.request.RequestExecutor;

/**
 * The core of the plugin is the central place to access the functionality of
 * this plugin.
 * 
 * <p>
 * Use the {@link BeeromatCoreActivator} in order to get an instance of the
 * core.
 * </p>
 * 
 * @author thomas
 */
public interface BeeromatCore {

	/**
	 * Return the raw input provider.
	 * 
	 * @return {@link RawInputProvider}, never <code>null</code>.
	 */
	public RawInputProvider getRawInputProvider();
	
	public DrawManager getDrawManager();
	
	public RequestExecutor getRequestExecutor();
	
	public KegManager getKegManager();
	
	public ConfigManager getConfigManager();
	
	

}

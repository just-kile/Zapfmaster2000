package de.zapfmaster2000.webservice.core;

import de.zapfmaster2000.webservice.achievement.AchievementManagerImpl;
import de.zapfmaster2000.webservice.config.ConfigManager;
import de.zapfmaster2000.webservice.config.ConfigManagerImpl;
import de.zapfmaster2000.webservice.draw.DrawManager;
import de.zapfmaster2000.webservice.draw.DrawManagerImpl;
import de.zapfmaster2000.webservice.handler.DrawingsHandler;
import de.zapfmaster2000.webservice.handler.LoginHandler;
import de.zapfmaster2000.webservice.handler.StatusHandler;
import de.zapfmaster2000.webservice.input.RawInputProvider;
import de.zapfmaster2000.webservice.input.RawInputProviderImpl;
import de.zapfmaster2000.webservice.keg.KegManager;
import de.zapfmaster2000.webservice.keg.KegManagerImpl;
import de.zapfmaster2000.webservice.request.RequestExecutor;
import de.zapfmaster2000.webservice.request.RequestExecutorImpl;

/**
 * Core that delivers access to the central functionality for the Zapfmaster2000
 * WebService.
 * 
 * @author thomas
 */
public class Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000Core instance;

	private RawInputProvider rawInputProvider;

	private DrawManager drawManager;

	private RequestExecutor requestExecutor;

	private KegManager kegManager;

	private ConfigManager configManager;

	private Zapfmaster2000Core() {
		instance = this;
		
		rawInputProvider = new RawInputProviderImpl();
		drawManager = new DrawManagerImpl(rawInputProvider);
		requestExecutor = new RequestExecutorImpl();
		kegManager = new KegManagerImpl();
		configManager = new ConfigManagerImpl();
		
		// handlers
		new StatusHandler(rawInputProvider, drawManager);
		DrawingsHandler drawingsHandler = new DrawingsHandler(drawManager);
		new LoginHandler(rawInputProvider, drawManager);

		new AchievementManagerImpl(drawingsHandler);
	}

	/**
	 * Returns the shared instance of the Zapfmaster2000Core.
	 * 
	 * @return never <code>null</code>.
	 */
	public static synchronized Zapfmaster2000Core getInstance() {
		if (instance == null) {
			instance = new Zapfmaster2000Core();
		}
		return instance;
	}

	public RawInputProvider getRawInputProvider() {
		return rawInputProvider;
	}

	public DrawManager getDrawManager() {
		return drawManager;
	}

	public RequestExecutor getRequestExecutor() {
		return requestExecutor;
	}

	public KegManager getKegManager() {
		return kegManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

}

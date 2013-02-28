package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.core.keg.KegService;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.registration.RegistrationService;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
import de.kile.zapfmaster2000.rest.impl.core.achievement.AchievementServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.auth.AuthServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.box.BoxServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.challenge.ChallengeServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.configuration.FileConfiguratioServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.keg.KegServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.news.NewsServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.push.PushServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.registration.RegistrationServiceImpl;
import de.kile.zapfmaster2000.rest.impl.core.transaction.TransactionServiceImpl;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {

	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;

	/** the transaction manager */
	private TransactionService transactionService;

	/** the configuration manager */
	private ConfigurationService configurationService;

	/** the box manager */
	private BoxService boxService;

	/** the auth service */
	private AuthService authService;

	/** the achievement service */
	private AchievementService achievementService;

	/** the news service */
	private NewsService newsService;

	/** the push service */
	private PushService pushService;

	/** the challenge service */
	private ChallengeService challengeService;

	/** the keg service */
	private KegService kegService;

	/** the registration service */
	private RegistrationService registrationService;

	private Zapfmaster2000CoreImpl() {
		transactionService = new TransactionServiceImpl();
		configurationService = new FileConfiguratioServiceImpl();
		boxService = new BoxServiceImpl();
		authService = new AuthServiceImpl();
		achievementService = new AchievementServiceImpl(boxService,
				transactionService);
		challengeService = new ChallengeServiceImpl();
		kegService = new KegServiceImpl();
		registrationService = new RegistrationServiceImpl();
		newsService = new NewsServiceImpl(boxService, achievementService,
				challengeService, kegService, registrationService);
		pushService = new PushServiceImpl(newsService, boxService,
				challengeService);
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
		return transactionService;
	}

	@Override
	public BoxService getBoxService() {
		return boxService;
	}

	@Override
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	@Override
	public AuthService getAuthService() {
		return authService;
	}

	@Override
	public AchievementService getAchievementService() {
		return achievementService;
	}

	@Override
	public NewsService getNewsService() {
		return newsService;
	}

	@Override
	public PushService getPushService() {
		return pushService;
	}

	@Override
	public ChallengeService getChallengeService() {
		return challengeService;
	}

	@Override
	public KegService getKegService() {
		return kegService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	/**
	 * Sets a transaction service. Use only for mocking in unit tests!
	 * 
	 * @param pTransactionManager
	 *            the service to set
	 */
	void setTransactionManager(TransactionService pTransactionManager) {
		transactionService = pTransactionManager;
	}

	/**
	 * Sets the configuration service. Use only for mocking in unit tests!
	 * 
	 * @param pConfigurationManager
	 *            the service to set
	 */
	void setConfigurationManager(ConfigurationService pConfigurationManager) {
		configurationService = pConfigurationManager;
	}

	/**
	 * Sets the box service. Use only for mocking in unit tests!
	 * 
	 * @param pBoxManager
	 *            the service to set
	 */
	void setBoxManager(BoxService pBoxManager) {
		boxService = pBoxManager;
	}

	/**
	 * Sets the auth service. Use only for mocking in unit tests!
	 * 
	 * @param pAuthService
	 *            the service to set
	 */
	void setAuthService(AuthService pAuthService) {
		authService = pAuthService;
	}

	/**
	 * Sets the achievement service. Use only for mocking in unit tests!
	 * 
	 * @param pAchievementService
	 *            the service to set
	 */
	void setAchievementService(AchievementService pAchievementService) {
		achievementService = pAchievementService;
	}

	/**
	 * Sets the news service. Use only for mocking in unit tests!
	 * 
	 * @param pNewsService
	 *            the service to set
	 */
	void setNewsService(NewsService pNewsService) {
		newsService = pNewsService;
	}

	/**
	 * Sets the push service. Use only for mocking in unit tests!
	 * 
	 * @param pPushService
	 *            the service to set
	 */
	void setPushService(PushService pPushService) {
		pushService = pPushService;
	}
}
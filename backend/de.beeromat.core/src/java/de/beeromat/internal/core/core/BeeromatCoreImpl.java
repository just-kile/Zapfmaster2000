package de.beeromat.internal.core.core;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.achievemnt.AchievementManager;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.core.BeeromatCore;
import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.request.RequestExecutor;
import de.beeromat.internal.core.achievement.AchievementManagerImpl;
import de.beeromat.internal.core.config.ConfigManagerImpl;
import de.beeromat.internal.core.draw.DrawManagerImpl;
import de.beeromat.internal.core.handler.DrawingsHandler;
import de.beeromat.internal.core.handler.LoginHandler;
import de.beeromat.internal.core.handler.StatusHandler;
import de.beeromat.internal.core.input.RawInputProviderImpl;
import de.beeromat.internal.core.input.RawInputProviderMock;
import de.beeromat.internal.core.keg.KegManagerImpl;
import de.beeromat.internal.core.request.RequestExecutorImpl;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.input.RawInputProvider;
import de.zapfmaster2000.webservice.keg.KegManager;

/**
 * Implemenation of {@link BeeromatCore}.
 * 
 * @author thomas
 */
public class BeeromatCoreImpl implements BeeromatCore {

	/** the raw input provider */
	private final RawInputProvider fRawInputProvider;

	/** the draw manager */
	private final DrawManager fDrawManager;

	/** request exeutor */
	private final RequestExecutor fRequestExecutor;
	
	private final KegManager fKegManager;
	
	private final ConfigManager fConfigManager;
	
	private final AchievementManager fAchievementManager;

	/**
	 * Constructor.
	 */
	public BeeromatCoreImpl() {
		//dummyQuery();
		
		fConfigManager = new ConfigManagerImpl();
		fRawInputProvider = new RawInputProviderImpl();
		//fRawInputProvider = new RawInputProviderMock();
		fDrawManager = new DrawManagerImpl(fRawInputProvider);
		fRequestExecutor = new RequestExecutorImpl();
		fKegManager = new KegManagerImpl();
		
		// handlers
		new StatusHandler(fRawInputProvider, fDrawManager);
		DrawingsHandler drawingsHandler = new DrawingsHandler(fDrawManager);
		new LoginHandler(fRawInputProvider, fDrawManager);

		
		fAchievementManager = new AchievementManagerImpl(drawingsHandler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RawInputProvider getRawInputProvider() {
		return fRawInputProvider;
	}

	@Override
	public DrawManager getDrawManager() {
		return fDrawManager;
	}

	@Override
	public RequestExecutor getRequestExecutor() {
		return fRequestExecutor;
	}

	@Override
	public KegManager getKegManager() {
		return fKegManager;
	}

	@Override
	public ConfigManager getConfigManager() {
		return fConfigManager;
	}
	
	void dummyQuery() {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		Query query = session
				.createQuery("select sum(d.realAmount) from Drawing as d where d.userId = "
						+ "21");
		List<?> result = query.list();
		tx.commit();

		if (!result.isEmpty() && result.get(0) instanceof Double) {
			double sum = (Double) result.get(0);
			System.out.println(sum);
		}
		System.exit(0);
	}
	
	
}

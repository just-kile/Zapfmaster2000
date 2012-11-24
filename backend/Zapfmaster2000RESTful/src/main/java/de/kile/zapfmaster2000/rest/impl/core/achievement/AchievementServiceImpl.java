package de.kile.zapfmaster2000.rest.impl.core.achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementServiceListener;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AchievementProcessorListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class AchievementServiceImpl implements AchievementService {

	private static final Logger LOG = Logger
			.getLogger(AchievementServiceImpl.class);

	private final List<AchievementServiceListener> listeners = new ArrayList<>();

	private final Map<Long, List<AbstractAchievementProcessor>> mapAccountId2Processors = new HashMap<>();

	private final AchievementProcessorListener achievementProcessorListener = createAchievementProcessorListener();

	public AchievementServiceImpl(BoxService pBoxService,
			TransactionService pTransactionService) {

		createAchievmentProcessors(pTransactionService);
		pBoxService.addListener(createBoxServiceListener());
	}

	@Override
	public void addListener(AchievementServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(AchievementServiceListener pListener) {
		listeners.remove(pListener);
	}

	private void notifyAchivementGained(GainedAchievement pGainedAchievement) {
		for (AchievementServiceListener listener : listeners) {
			listener.onAchievementGained(pGainedAchievement);
		}
	}

	private void createAchievmentProcessors(
			TransactionService pTransactionService) {
		List<AchievementDescriptor> descriptors = new AchievementRegistry()
				.createDescriptors();

		// create the achievement processors for all accounts
		Session session = pTransactionService.getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Account> result = session.createQuery("FROM Account").list();
		tx.commit();

		for (Account account : result) {
			mapAccountId2Processors.put(account.getId(),
					new ArrayList<AbstractAchievementProcessor>());
		}

		for (AchievementDescriptor desc : descriptors) {
			Achievement achievement = findAchievment(desc, pTransactionService);
			for (Account account : result) {
				try {
					AbstractAchievementProcessor processor = desc
							.getProcessor().newInstance();
					processor.init(account, achievement);
					processor.addListener(achievementProcessorListener);

					mapAccountId2Processors.get(account.getId()).add(processor);
				} catch (InstantiationException | IllegalAccessException e) {
					LOG.error("Could not create achivement processor", e);
				}
			}
		}
	}

	private Achievement findAchievment(AchievementDescriptor pDescriptor,
			TransactionService pTransactionService) {
		Session session = pTransactionService.getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Achievement> result = session
				.createQuery("SELECT a FROM Achievement a WHERE a.name = :name")
				.setString("name", pDescriptor.getName()).list();

		Achievement achievement = null;
		if (result.isEmpty()) {
			// create new achievement
			achievement = Zapfmaster2000Factory.eINSTANCE.createAchievement();
			achievement.setName(pDescriptor.getName());
			achievement.setDescription(pDescriptor.getDescription());
			achievement.setImagePath(pDescriptor.getImagePath());
			session.save(achievement);
		} else {
			achievement = result.get(0);
		}
		tx.commit();

		return achievement;
	}

	private BoxServiceListener createBoxServiceListener() {
		return new BoxServiceListener() {

			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {
			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				processAchievements(pBox.getAccount(), pDrawing);
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
			}
		};
	}

	private void processAchievements(Account pAccount, Drawing pDrawing) {
		List<AbstractAchievementProcessor> processors = mapAccountId2Processors
				.get(pAccount.getId());
		if (processors != null) {
			for (AbstractAchievementProcessor processor : processors) {
				if (processor.canGain(pDrawing.getUser())) {
					processor.process(pDrawing);
				}
			}
		} else {
			LOG.error("Could not find processors for account: " + pAccount);
		}

	}

	private AchievementProcessorListener createAchievementProcessorListener() {
		return new AchievementProcessorListener() {
			@Override
			public void onAchievementGained(GainedAchievement pGainedAchievement) {
				notifyAchivementGained(pGainedAchievement);
			}
		};
	}

}

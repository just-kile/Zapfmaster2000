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
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class AchievementServiceImpl implements AchievementService {

	private static final Logger LOG = Logger
			.getLogger(AchievementServiceImpl.class);

	private final List<AchievementServiceListener> listeners = new ArrayList<>();

	private final Map<Account, List<AbstractAchievementProcessor>> mapAccount2Processors = new HashMap<>();

	public AchievementServiceImpl(BoxService pBoxService,
			TransactionService pTransactionService) {

		createAchievmentProcessors(pTransactionService);
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
			mapAccount2Processors.put(account,
					new ArrayList<AbstractAchievementProcessor>());
		}

		for (AchievementDescriptor desc : descriptors) {
			Achievement achievement = findAchievment(desc, pTransactionService);
			for (Account account : result) {
				try {
					AbstractAchievementProcessor processor = desc
							.getProcessor().newInstance();
					processor.init(account, achievement);
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

}

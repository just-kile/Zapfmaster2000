package de.kile.zapfmaster2000.rest.impl.core.achievement.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationConstants;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

/**
 * Checks if a user earned a specific achievement.
 * 
 * @author Thomas Kipar
 */
public abstract class AbstractAchievementProcessor {

	private static final Logger LOG = Logger
			.getLogger(AbstractAchievementProcessor.class);

	private final List<AchievementProcessorListener> listeners = new ArrayList<>();

	private Achievement achievement;
	private Account account;

	public void init(Account pAccount, Achievement pAchievement) {
		account = pAccount;
		achievement = pAchievement;
	}

	public boolean canGain(User pUser) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<?> result = session
				.createQuery(
						"SELECT COUNT(*) FROM GainedAchievement a "
								+ "WHERE a.user.id = :userId and a.achievement.id = :achievementId")
				.setLong("userId", pUser.getId())
				.setLong("achievementId", achievement.getId()).list();
		long count = (Long) result.get(0);
		tx.commit();

		return count == 0;
	}

	/**
	 * Processes a finished drawing.
	 * 
	 * @param pDrawing
	 *            the user that finished the drawing
	 */
	public abstract void process(Drawing pDrawing);

	public void addListener(AchievementProcessorListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	public void removeListener(AchievementProcessorListener pListener) {
		listeners.remove(pListener);
	}

	public Account getAccount() {
		return account;
	}

	protected void gain(User pUser) {
		LOG.info("Gaining achievement " + achievement.getId() + " for user "
				+ pUser.getId());
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(achievement);
		session.update(pUser);

		GainedAchievement gained = Zapfmaster2000Factory.eINSTANCE
				.createGainedAchievement();
		gained.setAchievement(achievement);
		gained.setUser(pUser);
		gained.setDate(new Date());
		session.save(gained);

		tx.commit();
		notifyListenersAchievementGained(gained);
	}

	protected Achievement getAchievement() {
		return achievement;
	}

	protected final double getBeerSize() {
		return Zapfmaster2000Core.INSTANCE.getConfigurationService().getDouble(
				ConfigurationConstants.BEER_SIZE);
	}

	private void notifyListenersAchievementGained(
			GainedAchievement pGainedAchievement) {
		for (AchievementProcessorListener listener : listeners) {
			listener.onAchievementGained(pGainedAchievement);
		}
	}

}

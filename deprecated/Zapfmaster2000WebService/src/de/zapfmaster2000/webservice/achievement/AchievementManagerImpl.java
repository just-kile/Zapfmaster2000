package de.zapfmaster2000.webservice.achievement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.Assert;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.handler.DrawingsHandler;
import de.zapfmaster2000.webservice.handler.DrawingsHandlerListener;
import de.zapfmaster2000.webservice.model.db.User;

public class AchievementManagerImpl implements AchievementManager {

	private static final Logger LOG = Logger
			.getLogger(AchievementManagerImpl.class);

	private final List<AbstractAchievementProcessor> fProcessors = new ArrayList<AbstractAchievementProcessor>();

	public AchievementManagerImpl(DrawingsHandler pDrawingsHandler) {
		registerAchievements();
		pDrawingsHandler.addListener(createDrawManagerListener());
	}

	private void registerAchievements() {

		for (AchievementDescriptor achievement : AchievementRegistrator
				.getAchievements()) {
			registerAchievement(achievement);
		}
	}

	/**
	 * Registers the achievement as known processor.
	 * 
	 * <p>
	 * Will also create a db entry if the achievement does not exist already.
	 * </p>
	 * 
	 * @param pAchievement
	 */
	private void registerAchievement(AchievementDescriptor pAchievement) {
		Assert.isNotNull(pAchievement);
		LOG.debug("Register achievement: " + pAchievement.getName());

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<?> achievements = session.createQuery(
				"select a from Achievement as a where a.name = '"
						+ pAchievement.getName() + "'").list();

		de.zapfmaster2000.webservice.model.db.Achievement dbAchievement;
		if (achievements.isEmpty()) {
			LOG.debug("... adding achievement " + pAchievement.getName()
					+ " to db");
			dbAchievement = new de.zapfmaster2000.webservice.model.db.Achievement();
			dbAchievement.setName(pAchievement.getName());
			dbAchievement.setDescription(pAchievement.getDescription());
			dbAchievement.setImagePath(pAchievement.getImagepath());
			dbAchievement.setPublic(Boolean.valueOf(pAchievement.getPublic()));
			dbAchievement.setType(pAchievement.getType());
			session.save(dbAchievement);
		} else {
			LOG.debug("... achievement " + pAchievement.getName()
					+ " already exists in db");
			dbAchievement = (de.zapfmaster2000.webservice.model.db.Achievement) achievements
					.get(0);
		}

		tx.commit();

		AbstractAchievementProcessor processor = pAchievement.getProcessor();
		processor.init(dbAchievement);
		fProcessors.add(processor);
	}

	private DrawingsHandlerListener createDrawManagerListener() {
		return new DrawingsHandlerListener() {

			@Override
			public void onDrawingFinished(User pUser) {
				processAchievements(pUser);
			}
		};
	}

	private void processAchievements(User pUser) {
		Assert.isNotNull(pUser);

		for (AbstractAchievementProcessor processor : fProcessors) {
			try {
				if (processor.canGain(pUser)) {
					processor.process(pUser);
				}
			} catch (Throwable th) {
				LOG.error("Error while processing achievement", th);
			}
		}

	}

}

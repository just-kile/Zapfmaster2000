package de.beeromat.internal.core.achievement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.achievemnt.AbstractAchievementProcessor;
import de.beeromat.core.achievemnt.AchievementManager;
import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.draw.DrawManagerAdapter;
import de.beeromat.core.draw.DrawManagerListener;
import de.beeromat.core.extension.achievement.Achievement;
import de.beeromat.core.extension.achievement.AchievementExtension;
import de.beeromat.core.extension.achievement.AchievementExtensionResolver;
import de.beeromat.core.model.db.User;
import de.beeromat.internal.core.handler.DrawingsHandler;
import de.beeromat.internal.core.handler.DrawingsHandlerListener;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

public class AchievementManagerImpl implements AchievementManager {

	private static final Logger LOG = Logger
			.getLogger(AchievementManagerImpl.class);

	private final List<AbstractAchievementProcessor> fProcessors = new ArrayList<AbstractAchievementProcessor>();

	public AchievementManagerImpl(DrawingsHandler pDrawingsHandler) {
		try {
			registerAchievements();
		} catch (CoreException ex) {
			LOG.error("Could not resolve achievments", ex);
		}
		pDrawingsHandler.addListener(createDrawManagerListener());
	}

	private void registerAchievements() throws CoreException {
		AchievementExtensionResolver resolver = new AchievementExtensionResolver();
		AchievementExtension[] extensions = resolver.resolve();
		for (AchievementExtension extension : extensions) {
			for (Achievement achievement : extension.getAchievements()) {
				registerAchievement(achievement);
			}
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
	private void registerAchievement(Achievement pAchievement) {
		Assert.isNotNull(pAchievement);
		LOG.debug("Register achievement: " + pAchievement.getName());

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List achievements = session.createQuery(
				"select a from Achievement as a where a.name = '"
						+ pAchievement.getName() + "'").list();

		de.beeromat.core.model.db.Achievement dbAchievement;
		if (achievements.isEmpty()) {
			LOG.debug("... adding achievement " + pAchievement.getName()
					+ " to db");
			dbAchievement = new de.beeromat.core.model.db.Achievement();
			dbAchievement.setName(pAchievement.getName());
			dbAchievement.setDescription(pAchievement.getDescription());
			dbAchievement.setImagePath(pAchievement.getImagepath());
			dbAchievement.setPublic(Boolean.valueOf(pAchievement.getPublic()));
			dbAchievement.setType(pAchievement.getType());
			session.save(dbAchievement);
		} else {
			LOG.debug("... achievement " + pAchievement.getName()
					+ " already exists in db");
			dbAchievement = (de.beeromat.core.model.db.Achievement) achievements
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

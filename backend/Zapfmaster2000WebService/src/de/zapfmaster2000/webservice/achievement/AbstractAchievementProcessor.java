package de.zapfmaster2000.webservice.achievement;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.Assert;
import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.Achievement;
import de.zapfmaster2000.webservice.model.db.GainedAchievement;
import de.zapfmaster2000.webservice.model.db.News;
import de.zapfmaster2000.webservice.model.db.User;
import de.zapfmaster2000.webservice.request.RequestExecutor;
import de.zapfmaster2000.webservice.request.RequestType;

/**
 * Checks if a user earned a specific achievement.
 * 
 * @author thomas
 */
public abstract class AbstractAchievementProcessor {

	private static final Logger LOG = Logger
			.getLogger(AbstractAchievementProcessor.class);

	private Achievement fAchievement;

	public void init(Achievement pAchievement) {
		fAchievement = pAchievement;
	}

	public boolean canGain(User pUser) {
		Assert.isNotNull(pUser);
		boolean gained;

		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		Query query = session
				.createQuery("select a from GainedAchievement as a where a.userId = "
						+ pUser.getUserId()
						+ " and a.achievementId = "
						+ getAchievement().getAchievmentId());
		List<?> result = query.list();
		gained = !result.isEmpty();
		tx.commit();

		return !gained;
	}

	/**
	 * Processes a finished drawing.
	 * 
	 * @param pUser
	 *            the user that finished the drawing
	 * @param pAmount
	 *            the amount
	 */
	public abstract void process(User pUser);

	protected void gain(User... pUsers) {
		if (pUsers != null) {
			// write to db

			// read biggest latest group
			int groupId = 0;
			Date date = new Date();

			Transaction tx = null;
			Session session = SessionFactoryUtil.getInstance()
					.getCurrentSession();
			tx = session.beginTransaction();
			Query query = session
					.createQuery("select a from GainedAchievement as a order by groupId desc");
			query.setMaxResults(1);
			List<?> result = query.list();
			if (!result.isEmpty()) {
				GainedAchievement gained = (GainedAchievement) result.get(0);
				groupId = gained.getGroupId() + 1;
			}

			for (User user : pUsers) {
				LOG.debug("Gained achievement " + getAchievement().getName()
						+ " by user " + user.getName());

				GainedAchievement gained = new GainedAchievement();
				gained.setAchievementId(fAchievement.getAchievmentId());
				gained.setUserId(user.getUserId());
				gained.setDate(date);
				gained.setGroupId(groupId);
				session.save(gained);
			}

			// create news
			News news = new News();
			news.setImagePath(fAchievement.getImagePath());
			news.setContents(String.valueOf(groupId));
			news.setNewsType("ACHIEVEMENT");
			session.save(news);

			tx.commit();

			// send post
			RequestExecutor executor = Zapfmaster2000Core.getInstance()
					.getRequestExecutor();
			PostMethod post = executor
					.createPostMethod(RequestType.ACHIEVEMENT);
			post.addParameter("achievement",
					String.valueOf(getAchievement().getAchievmentId()));
			StringBuilder builder = new StringBuilder();
			boolean addSeparator = false;
			for (User user : pUsers) {
				if (addSeparator) {
					builder.append(";");
				} else {
					addSeparator = true;
				}
				builder.append(user.getUserId());
			}
			post.addParameter("user", builder.toString());
			executor.executePost(post);

		}
	}

	protected Achievement getAchievement() {
		return fAchievement;
	}

}

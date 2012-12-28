package de.kile.zapfmaster2000.rest.impl.core.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementServiceListener;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.box.LoginFailureReason;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeService;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeServiceListener;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class NewsServiceImpl implements NewsService {

	/** logger */
	private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

	/** listeners */
	private static List<NewsServiceListener> listeners = new ArrayList<>();

	public NewsServiceImpl(BoxService pBoxService,
			AchievementService pAchievementService,
			ChallengeService pChallengeService) {
		pBoxService.addListener(createBoxServiceListener());
		pAchievementService.addListener(createAchievementServiceListener());
		pChallengeService.addListener(createChallengeServiceListener());
	}

	@Override
	public void addListener(NewsServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}

	}

	@Override
	public void removeListener(NewsServiceListener pListener) {
		listeners.remove(pListener);
	}

	private AchievementServiceListener createAchievementServiceListener() {
		return new AchievementServiceListener() {
			@Override
			public void onAchievementGained(GainedAchievement pGainedAchievement) {
				postNewsAchievementGained(pGainedAchievement);
			}
		};
	}

	private BoxServiceListener createBoxServiceListener() {
		return new BoxServiceListener() {

			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				postNewsDrawFinished(pBox, pDrawing);
			}

			@Override
			public void onLogout(Box pBox, User pUser) {
			}

			@Override
			public void onLoginFailed(Box pBox, LoginFailureReason pReason,
					long pTag) {
			}
		};
	}

	private ChallengeServiceListener createChallengeServiceListener() {
		return new ChallengeServiceListener() {

			@Override
			public void onPendingChallengeCreated(Challenge pChallenge) {
			}

			@Override
			public void onChallengeStarted(Challenge pChallenge) {
				postNewsChallengeStarted(pChallenge);
			}

			@Override
			public void onChallengeFinished(Challenge pChallenge) {
				postNewsChallengeFinished(pChallenge);

			}

			@Override
			public void onChallengeDeclined(Challenge pChallenge) {
				postNewsChallengeDeclined(pChallenge);
			}
		};
	}

	private void postNewsDrawFinished(Box pBox, Drawing pDrawing) {
		LOG.debug("Posting news (draw finished): " + pDrawing.getId());

		// find system for the given box
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Account> accounts = session
				.createQuery("SELECT b.account FROM Box b WHERE b.id = :id")
				.setLong("id", pBox.getId()).list();
		session.update(pDrawing);

		DrawingNews news = null;
		if (accounts.size() == 1) {
			news = Zapfmaster2000Factory.eINSTANCE.createDrawingNews();
			news.setAccount(accounts.get(0));
			news.setDate(new Date());
			news.setDrawing(pDrawing);
			session.save(news);
		} else {
			LOG.error("Could not post news: No (unique) system found for box "
					+ pBox.getId());
		}

		tx.commit();

		if (news != null) {
			notifiyListenersNewsPosted(news);
		}
	}

	private void postNewsAchievementGained(GainedAchievement pGainedAchievement) {
		LOG.debug("Posting news (achievement gained): "
				+ pGainedAchievement.getId());

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.update(pGainedAchievement);
		session.update(pGainedAchievement.getUser());
		session.update(pGainedAchievement.getUser().getAccount());

		AchievementNews news = Zapfmaster2000Factory.eINSTANCE
				.createAchievementNews();
		news.setAccount(pGainedAchievement.getUser().getAccount());
		news.setDate(new Date());
		news.setGainedAchievment(pGainedAchievement);
		session.save(news);

		tx.commit();
		notifiyListenersNewsPosted(news);
	}

	private void postNewsChallengeStarted(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(challenge1v1);
			session.update(challenge1v1.getUser1());
			session.update(challenge1v1.getUser1().getAccount());

			Challenge1v1StartedNews news = Zapfmaster2000Factory.eINSTANCE
					.createChallenge1v1StartedNews();
			news.setAccount(challenge1v1.getUser1().getAccount());
			news.setDate(new Date());
			news.setChallenge(challenge1v1);
			session.save(news);

			tx.commit();
			notifiyListenersNewsPosted(news);
		} else {
			LOG.error("Unknown challenge type:" + pChallenge.eClass().getName());
		}
	}

	private void postNewsChallengeFinished(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(challenge1v1);
			session.update(challenge1v1.getUser1());
			session.update(challenge1v1.getUser1().getAccount());

			Challenge1v1DoneNews news = Zapfmaster2000Factory.eINSTANCE
					.createChallenge1v1DoneNews();
			news.setAccount(challenge1v1.getUser1().getAccount());
			news.setDate(new Date());
			news.setChallenge(challenge1v1);
			session.save(news);

			tx.commit();
			notifiyListenersNewsPosted(news);
		} else {
			LOG.error("Unknown challenge type:" + pChallenge.eClass().getName());
		}
	}

	private void postNewsChallengeDeclined(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(challenge1v1);
			session.update(challenge1v1.getUser1());
			session.update(challenge1v1.getUser1().getAccount());

			Challenge1v1DeclinedNews news = Zapfmaster2000Factory.eINSTANCE
					.createChallenge1v1DeclinedNews();
			news.setAccount(challenge1v1.getUser1().getAccount());
			news.setDate(new Date());
			news.setChallenge(challenge1v1);
			session.save(news);

			tx.commit();
			notifiyListenersNewsPosted(news);
		} else {
			LOG.error("Unknown challenge type:" + pChallenge.eClass().getName());
		}
	}

	private void notifiyListenersNewsPosted(News pNews) {
		for (NewsServiceListener listener : listeners) {
			listener.onNewsPosted(pNews);
		}
	}

}

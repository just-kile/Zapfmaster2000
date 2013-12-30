package de.kile.zapfmaster2000.rest.impl.core.challenge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeService;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeServiceListener;
import de.kile.zapfmaster2000.rest.core.challenge.StatusAwareAsynchronousResponse;
import de.kile.zapfmaster2000.rest.impl.core.transaction.SharedQueries;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class ChallengeServiceImpl implements ChallengeService {

	public List<UserLoginStatus> users = new LinkedList<>();

	private final List<ChallengeServiceListener> listeners = new ArrayList<>();

	private final ChallengeEvaluator evaluator;

	public ChallengeServiceImpl() {
		evaluator = new ChallengeEvaluator(this);
		evaluator.start();
	}

	@Override
	public void rememberUser(final User pUser,
			StatusAwareAsynchronousResponse pReponse) {
		if (pUser != null && pReponse != null) {
			removeUsers();
			synchronized (users) {
				// if the user is already in the list, remove him before adding
				// once more
				users = Lists.newArrayList(Collections2.filter(users,
						new Predicate<UserLoginStatus>() {
							public boolean apply(UserLoginStatus pStatus) {
								return pUser.getId() != pStatus.user.getId();
							}
						}));

				users.add(new UserLoginStatus(System.currentTimeMillis(),
						pUser, pReponse));
			}
		}
	}

	@Override
	public List<User> retrieveLoggedInUsers(Account pAccount) {

		// collect the users that are polling the challenge service using
		// reverse ajax
		List<User> usersForAccount = new ArrayList<>();
		if (pAccount != null) {
			removeUsers();
			for (UserLoginStatus status : users) {
				if (status.user.getAccount().getId() == pAccount.getId()) {
					usersForAccount.add(status.user);
				}
			}
		}

		// collect all users that have a google cloud message token as well
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery(
				"SELECT t.user FROM Token t WHERE t.user IS NOT NULL "
						+ "AND t.googleCloudMessagingToken IS NOT NULL "
						+ "AND t.account.id = :accountId").setLong(
				"accountId", pAccount.getId());
		
		@SuppressWarnings("unchecked")
		List<User> results = query.list();
		usersForAccount.addAll(results);
		tx.commit();
		
		Set<Long> userIds = new HashSet<>();
		List<User> result = new ArrayList<>();
		for (User user : usersForAccount) {
			if (userIds.add(user.getId())) {
				result.add(user);
			}
		}

		return result;
	}

	@Override
	public Challenge1v1 createPendingChallenge1v1(User pUser1, User pUser2,
			int pDuration) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		pUser1 = (User) session.load(Zapfmaster2000Package.eINSTANCE.getUser()
				.getName(), pUser1.getId());
		pUser2 = (User) session.load(Zapfmaster2000Package.eINSTANCE.getUser()
				.getName(), pUser2.getId());
		Challenge1v1 challenge = Zapfmaster2000Factory.eINSTANCE
				.createChallenge1v1();
		challenge.setUser1(pUser1);
		challenge.setUser2(pUser2);
		challenge.setState(ChallengeState.PENDING);
		challenge.setDuration(pDuration);
		session.save(challenge);
		tx.commit();

		notifiyPendingChallengeCreated(challenge);

		return challenge;
	}

	@Override
	public void startChallenge(Challenge pChallenge) {
		if (pChallenge != null
				&& pChallenge.getState() == ChallengeState.PENDING) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			pChallenge = (Challenge) session.load(
					Zapfmaster2000Package.eINSTANCE.getChallenge().getName(),
					pChallenge.getId());
			pChallenge.setStartTime(new Date());
			pChallenge.setState(ChallengeState.RUNNING);
			session.save(pChallenge);
			tx.commit();

			notifiyChallengeStarted(pChallenge);
		}

		synchronized (evaluator) {
			evaluator.notify();
		}

	}

	@Override
	public void declineChallenge(Challenge pChallenge) {
		if (pChallenge != null
				&& pChallenge.getState() == ChallengeState.PENDING) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			pChallenge = (Challenge) session.load(
					Zapfmaster2000Package.eINSTANCE.getChallenge().getName(),
					pChallenge.getId());
			pChallenge.setState(ChallengeState.DECLINED);
			session.save(pChallenge);
			tx.commit();

			notifyChallengeDeclined(pChallenge);
		}
	}

	@Override
	public void addListener(ChallengeServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(ChallengeServiceListener pListener) {
		listeners.remove(pListener);
	}

	/**
	 * Evaluates the given challenge, will set its state to done and notify all
	 * listeners afterwards.
	 * 
	 * @param pChallenge
	 *            the challenge to evaluate
	 */
	void evaluateChallenge(Challenge1v1 pChallenge) {
		if (pChallenge != null) {

			Date startTime = pChallenge.getStartTime();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, pChallenge.getDuration());
			Date endTime = cal.getTime();

			double amountUser1 = SharedQueries.retrieveDrawingAmount(pChallenge
					.getUser1().getId(), startTime, endTime);
			double amountUser2 = SharedQueries.retrieveDrawingAmount(pChallenge
					.getUser2().getId(), startTime, endTime);

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			pChallenge = (Challenge1v1) session
					.load(Zapfmaster2000Package.eINSTANCE.getChallenge1v1()
							.getName(), pChallenge.getId());

			pChallenge.setState(ChallengeState.FINISHED);

			if (amountUser1 > amountUser2) {
				pChallenge.setWinner(pChallenge.getUser1());
			} else if (amountUser2 > amountUser1) {
				pChallenge.setWinner(pChallenge.getUser2());
			} // else no one wins

			session.save(pChallenge);
			tx.commit();
		}

		notifyChallengeFinished(pChallenge);
	}

	private void removeUsers() {
		List<UserLoginStatus> usersToRemove = new ArrayList<>();
		long minTime = System.currentTimeMillis()
				- PlatformConstants.ASYNC_TIMEOUT;
		synchronized (users) {
			for (UserLoginStatus status : users) {
				if (status.time < minTime || status.reponse.wasAnswered()) {
					usersToRemove.add(status);
				}
			}
			users.removeAll(usersToRemove);
		}
	}

	private void notifiyPendingChallengeCreated(Challenge pChallenge) {
		for (ChallengeServiceListener listener : listeners) {
			listener.onPendingChallengeCreated(pChallenge);
		}
	}

	private void notifyChallengeDeclined(Challenge pChallenge) {
		for (ChallengeServiceListener listener : listeners) {
			listener.onChallengeDeclined(pChallenge);
		}
	}

	private void notifiyChallengeStarted(Challenge pChallenge) {
		for (ChallengeServiceListener listener : listeners) {
			listener.onChallengeStarted(pChallenge);
		}
	}

	private void notifyChallengeFinished(Challenge pChallenge) {
		for (ChallengeServiceListener listener : listeners) {
			listener.onChallengeFinished(pChallenge);
		}
	}

	private class UserLoginStatus {
		private long time;
		private User user;
		private StatusAwareAsynchronousResponse reponse;

		private UserLoginStatus(long pTime, User pUser,
				StatusAwareAsynchronousResponse pReponse) {
			time = pTime;
			user = pUser;
			reponse = pReponse;
		}
	}
}

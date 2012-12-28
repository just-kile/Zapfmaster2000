package de.kile.zapfmaster2000.rest.impl.core.challenge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

public class ChallengeServiceImpl implements ChallengeService {

	public final List<UserLoginStatus> users = new LinkedList<>();

	private final List<ChallengeServiceListener> listeners = new ArrayList<>();
	
	private final ChallengeEvaluator evaluator;
	
	public ChallengeServiceImpl() {
		evaluator = new ChallengeEvaluator(this);
		evaluator.start();
	}

	@Override
	public void rememberUser(User pUser,
			StatusAwareAsynchronousResponse pReponse) {
		removeUsers();
		synchronized (users) {
			users.add(new UserLoginStatus(System.currentTimeMillis(), pUser,
					pReponse));
		}
	}

	@Override
	public List<User> retrieveLoggedInUsers(Account pAccount) {
		List<User> usersForAccount = new ArrayList<>();
		if (pAccount != null) {
			removeUsers();
			for (UserLoginStatus status : users) {
				if (status.user.getAccount().getId() == pAccount.getId()) {
					usersForAccount.add(status.user);
				}
			}
		}
		return usersForAccount;
	}

	@Override
	public Challenge1v1 createPendingChallenge1v1(User pUser1, User pUser2,
			int pDuration) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(pUser1);
		session.update(pUser2);
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
			session.update(pChallenge);
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
			session.update(pChallenge);
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
			session.update(pChallenge);
			
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

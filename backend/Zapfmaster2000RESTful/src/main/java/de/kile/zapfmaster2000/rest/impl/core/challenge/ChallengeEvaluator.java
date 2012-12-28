package de.kile.zapfmaster2000.rest.impl.core.challenge;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState;

class ChallengeEvaluator extends Thread {

	private static final Logger LOG = Logger
			.getLogger(ChallengeEvaluator.class);

	private final ChallengeServiceImpl service;

	public ChallengeEvaluator(ChallengeServiceImpl pService) {
		service = pService;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Challenge1v1 challenge = retriveNextChallengeToFinish();
				synchronized (this) {
					if (challenge == null) {
						wait();
					} else {
						Calendar cal = Calendar.getInstance();
						cal.setTime(challenge.getStartTime());
						cal.add(Calendar.MINUTE, challenge.getDuration());
						long diff = cal.getTimeInMillis()
								- System.currentTimeMillis();
						if (diff > 0) {
							wait(diff);
						} else {
							service.evaluateChallenge(challenge);
						}
					}
				}
			} catch (InterruptedException ex) {
				LOG.error("Error while waiting for next challenge to evalute");
			}
		}
	}

	/**
	 * Retrieves the next challenge that is about to finish.
	 * 
	 * @return the next 1v1 challenge that finishes or <code>null</code> if
	 *         there is no such challenge.
	 */
	private Challenge1v1 retriveNextChallengeToFinish() {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Challenge1v1> result = session
				.createQuery(
						"SELECT c FROM Challenge1v1 c "
								+ "JOIN FETCH c.user1 JOIN FETCH c.user2 "
								+ "WHERE c.state = :running "
								+ "ORDER BY c.startTime + MINUTE(c.duration) DESC")
				.setParameter("running", ChallengeState.RUNNING)
				.setMaxResults(1).list();
		tx.commit();

		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
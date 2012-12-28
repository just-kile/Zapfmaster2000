package de.kile.zapfmaster2000.rest.core.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.api.challenge.ChallengeOverviewReponse;
import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse.Type;
import de.kile.zapfmaster2000.rest.api.news.Challenge1v1NewsResponse.ChallengeParticipant;
import de.kile.zapfmaster2000.rest.impl.core.transaction.SharedQueries;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class ChallengeAdapter {

	private static Logger LOG = Logger.getLogger(ChallengeAdapter.class);

	public ChallengeOverviewReponse adaptChallenge(Challenge1v1 pChallenge) {

		ChallengeOverviewReponse news = new ChallengeOverviewReponse();

		news.setImage("images/others/1v1.jpg");
		news.loadStartDate(pChallenge.getStartTime());
		news.setChallengeDuration(pChallenge.getDuration());
		news.setChallengeId(pChallenge.getId());

		switch (pChallenge.getState()) {
		case RUNNING:
			news.setType(Type.CHALLENGE_STARTED);
			break;
		case DECLINED:
			news.setType(Type.CHALLENGE_DECLINED);
			break;
		case FINISHED:
			news.setType(Type.CHALLENGE_DONE);
			break;
		default:
			LOG.warn("Unsupported challenge type (for news): "
					+ pChallenge.getState());
		}

		news.getTeam1().add(
				adaptChallengeParticipant(pChallenge, pChallenge.getUser1()));
		news.getTeam2().add(
				adaptChallengeParticipant(pChallenge, pChallenge.getUser2()));

		return news;
	}

	private ChallengeParticipant adaptChallengeParticipant(
			Challenge1v1 pChallenge, User pUser) {
		ChallengeParticipant participant = new ChallengeParticipant();
		participant.setUserId(pUser.getId());
		participant.setUserName(pUser.getName());
		participant.setUserImage(pUser.getImagePath());
		participant.setWon(pUser == pChallenge.getWinner());

		if (pChallenge.getState() == ChallengeState.FINISHED
				|| pChallenge.getState() == ChallengeState.RUNNING) {
			Date from = pChallenge.getStartTime();

			Calendar cal = Calendar.getInstance();
			cal.setTime(from);
			cal.add(Calendar.MINUTE, pChallenge.getDuration());
			Date to = cal.getTime();

			participant.setAmount(SharedQueries.retrieveDrawingAmount(
					pUser.getId(), from, to));
		}

		return participant;
	}
}

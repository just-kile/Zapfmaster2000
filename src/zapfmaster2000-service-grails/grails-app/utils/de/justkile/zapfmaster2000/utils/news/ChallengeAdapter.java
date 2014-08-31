package de.justkile.zapfmaster2000.utils.news;

import java.util.Calendar;
import java.util.Date;

import de.justkile.zapfmaster2000.model.Challenge1v1;
import de.justkile.zapfmaster2000.model.User;
import de.justkile.zapfmaster2000.services.SharedQueriesService;
import org.apache.log4j.Logger;

public class ChallengeAdapter {

	private static Logger LOG = Logger.getLogger(ChallengeAdapter.class);
    private SharedQueriesService sharedQueriesService;

	public ChallengeOverviewReponse adaptChallenge(Challenge1v1 pChallenge) {

		ChallengeOverviewReponse news = new ChallengeOverviewReponse();

		news.setImage("images/others/1v1.jpg");
		news.loadStartDate(pChallenge.getStartTime());
		news.setChallengeDuration(pChallenge.getDuration());
		news.setChallengeId(pChallenge.getId());

		switch (pChallenge.getState()) {
		case RUNNING:
			news.setType(AbstractNewsResponse.Type.CHALLENGE_STARTED);
			news.setImage("images/others/challengeStarted.jpg");
			break;
		case DECLINED:
			news.setType(AbstractNewsResponse.Type.CHALLENGE_DECLINED);
			news.setImage("images/others/challengeDeclined.jpg");
			break;
		case FINISHED:
			news.setType(AbstractNewsResponse.Type.CHALLENGE_DONE);
			news.setImage("images/others/challengeDone.jpg");
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

	private Challenge1v1NewsResponse.ChallengeParticipant adaptChallengeParticipant(
			Challenge1v1
                    pChallenge, User pUser) {
		Challenge1v1NewsResponse.ChallengeParticipant participant = new Challenge1v1NewsResponse.ChallengeParticipant();
		participant.setUserId(pUser.getId());
		participant.setUserName(pUser.getName());
		participant.setUserImage(pUser.getImagePath());
		participant.setWon(pUser == pChallenge.getWinner());

		if (pChallenge.getState() == Challenge1v1.State.FINISHED
				|| pChallenge.getState() == Challenge1v1.State.RUNNING) {
			Date from = pChallenge.getStartTime();

			Calendar cal = Calendar.getInstance();
			cal.setTime(from);
			cal.add(Calendar.MINUTE, pChallenge.getDuration());
			Date to = cal.getTime();

			participant.setAmount(sharedQueriesService.retrieveDrawingAmount(
					pUser.getId(), from, to));
		}

		return participant;
	}
}

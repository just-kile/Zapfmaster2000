package de.kile.zapfmaster2000.rest.core.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.AchievementNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.Challenge1v1NewsResponse;
import de.kile.zapfmaster2000.rest.api.news.DrawingNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.Challenge1v1NewsResponse.ChallengeParticipant;
import de.kile.zapfmaster2000.rest.impl.core.transaction.SharedQueries;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DoneNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.ChallengeState;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class NewsAdapter {

	private static final Logger LOG = Logger.getLogger(NewsAdapter.class);

	public AbstractNewsResponse adapt(News pNews) {
		AbstractNewsResponse newsResponse = null;

		switch (pNews.eClass().getClassifierID()) {
		case Zapfmaster2000Package.DRAWING_NEWS:
			DrawingNews drawingNews = (DrawingNews) pNews;
			newsResponse = adaptDrawingNews(drawingNews);
			break;
		case Zapfmaster2000Package.ACHIEVEMENT_NEWS:
			AchievementNews achievementNews = (AchievementNews) pNews;
			newsResponse = adaptAchievementNews(achievementNews);
			break;
		case Zapfmaster2000Package.CHALLENGE1V1_STARTED_NEWS:
			Challenge1v1StartedNews startedNews = (Challenge1v1StartedNews) pNews;
			newsResponse = adaptChallenge1v1StartedNews(startedNews);
			break;
		case Zapfmaster2000Package.CHALLENGE1V1_DECLINED_NEWS:
			Challenge1v1DeclinedNews declinedNews = (Challenge1v1DeclinedNews) pNews;
			newsResponse = adaptChallenge1v1DeclinedNews(declinedNews);
			break;
		case Zapfmaster2000Package.CHALLENGE1V1_DONE_NEWS:
			Challenge1v1DoneNews doneNews = (Challenge1v1DoneNews) pNews;
			newsResponse = adaptChallenge1v1DonedNews(doneNews);
			break;

		default:
			LOG.error("Unsupported news type: " + pNews.getClass().getName());
		}

		if (newsResponse != null) {
			newsResponse.loadDate(pNews.getDate());
		}
		return newsResponse;
	}

	private AbstractNewsResponse adaptDrawingNews(DrawingNews pNews) {
		DrawingNewsResponse drawingResp = new DrawingNewsResponse();
		Drawing drawing = pNews.getDrawing();

		drawingResp.loadDate(pNews.getDate());
		drawingResp.setAmount(drawing.getAmount());
		drawingResp.setKegId(drawing.getKeg().getId());
		drawingResp.setBrand(drawing.getKeg().getBrand());
		drawingResp.setLocation(drawing.getKeg().getBox().getLocation());
		drawingResp.setImage(drawing.getUser().getImagePath());
		drawingResp.setUserId(drawing.getUser().getId());
		drawingResp.setUserName(drawing.getUser().getName());

		return drawingResp;
	}

	private AbstractNewsResponse adaptAchievementNews(AchievementNews pNews) {
		AchievementNewsResponse achievementResp = new AchievementNewsResponse();
		GainedAchievement gainedAchievement = pNews.getGainedAchievment();

		achievementResp.loadDate(pNews.getDate());
		achievementResp.setImage(gainedAchievement.getAchievement()
				.getImagePath());
		achievementResp.setUserName(gainedAchievement.getUser().getName());
		achievementResp.setUserId(gainedAchievement.getUser().getId());
		achievementResp.setAchievementName(gainedAchievement.getAchievement()
				.getName());
		achievementResp.setAchievementId(gainedAchievement.getUser().getId());

		return achievementResp;
	}

	private AbstractNewsResponse adaptChallenge1v1StartedNews(
			Challenge1v1StartedNews pStartedNews) {
		Challenge1v1NewsResponse news = new Challenge1v1NewsResponse();

		news.loadDate(pStartedNews.getDate());
		news.setImage("images/others/1v1.jpg");
		news.loadStartDate(pStartedNews.getChallenge().getStartTime());
		news.setChallengeDuration(pStartedNews.getChallenge().getDuration());
		news.setChallengeId(pStartedNews.getChallenge().getId());

		news.getTeam1().add(
				adaptChallengeParticipant(pStartedNews.getChallenge(),
						pStartedNews.getChallenge().getUser1()));
		news.getTeam2().add(
				adaptChallengeParticipant(pStartedNews.getChallenge(),
						pStartedNews.getChallenge().getUser1()));
		
		return news;
	}

	private AbstractNewsResponse adaptChallenge1v1DeclinedNews(
			Challenge1v1DeclinedNews pDeclinedNews) {
		Challenge1v1NewsResponse news = new Challenge1v1NewsResponse();

		news.loadDate(pDeclinedNews.getDate());
		news.setImage("images/others/1v1.jpg");
		news.loadStartDate(pDeclinedNews.getChallenge().getStartTime());
		news.setChallengeDuration(pDeclinedNews.getChallenge().getDuration());
		news.setChallengeId(pDeclinedNews.getChallenge().getId());

		news.getTeam1().add(
				adaptChallengeParticipant(pDeclinedNews.getChallenge(),
						pDeclinedNews.getChallenge().getUser1()));
		news.getTeam2().add(
				adaptChallengeParticipant(pDeclinedNews.getChallenge(),
						pDeclinedNews.getChallenge().getUser1()));
		
		return news;
	}

	private AbstractNewsResponse adaptChallenge1v1DonedNews(
			Challenge1v1DoneNews pDoneNews) {
		Challenge1v1NewsResponse news = new Challenge1v1NewsResponse();

		news.loadDate(pDoneNews.getDate());
		news.setImage("images/others/1v1.jpg");
		news.loadStartDate(pDoneNews.getChallenge().getStartTime());
		news.setChallengeDuration(pDoneNews.getChallenge().getDuration());
		news.setChallengeId(pDoneNews.getChallenge().getId());

		news.getTeam1().add(
				adaptChallengeParticipant(pDoneNews.getChallenge(),
						pDoneNews.getChallenge().getUser1()));
		news.getTeam2().add(
				adaptChallengeParticipant(pDoneNews.getChallenge(),
						pDoneNews.getChallenge().getUser1()));
		
		return news;
	}

	private ChallengeParticipant adaptChallengeParticipant(
			Challenge1v1 pChallenge, User pUser) {
		ChallengeParticipant participant = new ChallengeParticipant();
		participant.setUserId(pUser.getId());
		participant.setUserName(pUser.getName());
		participant.setUserImage(pUser.getImagePath());
		participant.setWon(pUser == pChallenge.getWinner());

		if (pChallenge.getState() == ChallengeState.FINISHED) {
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

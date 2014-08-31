package de.justkile.zapfmaster2000.utils.news

/**
 * Created by thomas on 31.08.14.
 */
import de.justkile.zapfmaster2000.model.Drawing;
import de.justkile.zapfmaster2000.model.achievement.GainedAchievement;
import de.justkile.zapfmaster2000.model.news.*;

public class NewsAdapter {

    public AbstractNewsResponse adapt(News pNews) throws Exception {
        AbstractNewsResponse newsResponse = null;

        if (pNews instanceof  DrawingNews ) {
            DrawingNews drawingNews = (DrawingNews) pNews;
            newsResponse = adaptDrawingNews(drawingNews);
        } else if (pNews instanceof AchievementNews) {
            AchievementNews achievementNews = (AchievementNews) pNews;
            newsResponse = adaptAchievementNews(achievementNews);
        } else if (pNews instanceof Challenge1v1StartedNews) {
            Challenge1v1StartedNews startedNews = (Challenge1v1StartedNews) pNews;
            newsResponse = adaptChallenge1v1StartedNews(startedNews);
        } else if (pNews instanceof Challenge1v1DeclinedNews) {
            Challenge1v1DeclinedNews declinedNews = (Challenge1v1DeclinedNews) pNews;
            newsResponse = adaptChallenge1v1DeclinedNews(declinedNews);
        } else if (pNews instanceof Challenge1v1DoneNews) {
            Challenge1v1DoneNews doneNews = (Challenge1v1DoneNews) pNews;
            newsResponse = adaptChallenge1v1DonedNews(doneNews);
        } else if (pNews instanceof NewKegNews) {
            NewKegNews newKegNews = (NewKegNews) pNews;
            newsResponse = adaptNewKegNews(newKegNews);
        } else if (pNews instanceof NewUserNews) {
            NewUserNews newUserNews = (NewUserNews) pNews;
            newsResponse = adaptNewUserNews(newUserNews);
        } else {
            throw new Exception("Unsupported news type: " + pNews.toString());
        }

        if (newsResponse != null) {
            newsResponse.loadDate(pNews.getDate());
        }
        return newsResponse;
    }

    private AbstractNewsResponse adaptDrawingNews(DrawingNews pNews) {
        DrawingNewsResponse drawingResp = new DrawingNewsResponse();

        Drawing.withTransaction {
            Drawing drawing = Drawing.get(pNews.getDrawing().id)
            drawingResp.loadDate(pNews.getDate());
            drawingResp.setAmount(drawing.getAmount());
            drawingResp.setKegId(drawing.getKeg().getId());
            drawingResp.setBrand(drawing.getKeg().getBrand());
            drawingResp.setLocation(drawing.getKeg().getZapfKit().getName());
            drawingResp.setImage(drawing.getUser().getImagePath());
            drawingResp.setUserId(drawing.getUser().getId());
            drawingResp.setUserName(drawing.getUser().getName());
        }

        return drawingResp;
    }

    private AbstractNewsResponse adaptAchievementNews(AchievementNews pNews) {
        AchievementNewsResponse achievementResp = new AchievementNewsResponse();
        GainedAchievement gainedAchievement = pNews.getGainedAchievement();

        achievementResp.loadDate(pNews.getDate());
        achievementResp.setImage(gainedAchievement.getAchievement()
                .getImagePath());
        achievementResp.setUserName(gainedAchievement.getUser().getName());
        achievementResp.setUserId(gainedAchievement.getUser().getId());
        achievementResp.setAchievementName(gainedAchievement.getAchievement()
                .getName());
        achievementResp.setAchievementId(gainedAchievement.getAchievement().getId());

        return achievementResp;
    }

    private AbstractNewsResponse adaptChallenge1v1StartedNews(
            Challenge1v1StartedNews pStartedNews) {
        ChallengeOverviewReponse news = new ChallengeAdapter()
                .adaptChallenge(pStartedNews.getChallenge());
        news.loadDate(pStartedNews.getDate());

        news.setImage("images/others/challengeStarted.jpg");
        news.setType(AbstractNewsResponse.Type.CHALLENGE_STARTED); // it is a challenge started news!
        // However, previous adaption
        // may detect that the challenge
        // is done already
        return news;
    }

    private AbstractNewsResponse adaptChallenge1v1DeclinedNews(
            Challenge1v1DeclinedNews pDeclinedNews) {
        AbstractNewsResponse news = new ChallengeAdapter()
                .adaptChallenge(pDeclinedNews.getChallenge());
        news.loadDate(pDeclinedNews.getDate());
        return news;
    }

    private AbstractNewsResponse adaptChallenge1v1DonedNews(
            Challenge1v1DoneNews pDoneNews) {
        AbstractNewsResponse news = new ChallengeAdapter()
                .adaptChallenge(pDoneNews.getChallenge());
        news.loadDate(pDoneNews.getDate());
        return news;
    }

    private AbstractNewsResponse adaptNewKegNews(NewKegNews pNewKegNews) {
        NewKegNewsReponse response = new NewKegNewsReponse();

        response.loadDate(pNewKegNews.getDate());
        response.setImage("images/others/newkeg.jpg");
        response.setSize(pNewKegNews.getKeg().getSize());
        response.setBrand(pNewKegNews.getKeg().getBrand());
        response.setKegId(pNewKegNews.getKeg().getId());
        response.setBoxId(pNewKegNews.getKeg().getZapfKit().getId());
        response.setLocation(pNewKegNews.getKeg().getZapfKit().getName());

        return response;
    }

    private AbstractNewsResponse adaptNewUserNews(NewUserNews pNews) {
        NewUserNewsResponse response = new NewUserNewsResponse();

        response.loadDate(pNews.getDate());
        response.setImage("images/others/newuser.jpg");
        response.setUserId(pNews.getUser().getId());
        response.setUserName(pNews.getUser().getName());

        return response;

    }

}


package de.kile.zapfmaster2000.rest.core.util;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.AchievementNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.DrawingNewsResponse;
import de.kile.zapfmaster2000.rest.api.news.NewsResource;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
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
		case Zapfmaster2000Package.OTHER_NEWS:
		default:
			LOG.error("Unsupported news type: " + pNews.getClass().getName());
		}

		if (newsResponse != null) {
			newsResponse.setDate(pNews.getDate());
		}
		return newsResponse;
	}

	private AbstractNewsResponse adaptDrawingNews(DrawingNews pNews) {
		DrawingNewsResponse drawingResp = new DrawingNewsResponse();
		Drawing drawing = pNews.getDrawing();

		drawingResp.setDate(pNews.getDate());
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

		achievementResp.setDate(pNews.getDate());
		achievementResp.setImage(gainedAchievement.getAchievement()
				.getImagePath());
		achievementResp.setUserName(gainedAchievement.getUser().getName());
		achievementResp.setUserId(gainedAchievement.getUser().getId());
		achievementResp.setAchievementName(gainedAchievement.getAchievement()
				.getName());
		achievementResp.setAchievementId(gainedAchievement.getUser().getId());

		return achievementResp;
	}
}

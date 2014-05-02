package de.kile.zapfmaster2000.rest.api.news;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AchievementNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1DeclinedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1StartedNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewKegNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewUserNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestNewsResource extends AbstractMockingTest {

	private DrawingNews drawingNews;
	private AchievementNews achievementNews;
	private Challenge1v1StartedNews challenge1v1StartedNews;
	private Challenge1v1DeclinedNews challenge1v1DeclinedNews;
	private NewKegNews newKegNews;
	private NewUserNews newUserNews;

	@Before
	public void setupNews() {

		Account account = createAccount("account");
		Box box = createBox("box", "loc", "1.0", account);
		Keg keg = createKeg("brand", createDate(2014, 01, 01, 10, 10, 0), null,
				50, box);
		User user = createUser("name", "img", "pw", 123, Sex.MALE, 50,
				UserType.USER, account);
		User user2 = createUser("name2", "img2", "pw", 123, Sex.MALE, 50,
				UserType.USER, account);
		Drawing drawing = createDrawing(0.3,
				createDate(2014, 01, 01, 10, 10, 0), keg, user);
		Achievement achievement = createAchievement("achievement", "desc",
				"achImage");
		GainedAchievement gainedAchievement = createGainedAchievement(
				createDate(2014, 01, 01, 10, 10, 0), user, achievement);
		Challenge1v1 challenge1v1 = createChallenge1v1(user, user2, null, 10);

		drawingNews = createDrawingNews(account,
				createDate(2014, 01, 01, 10, 10, 0), drawing);
		achievementNews = createAchievementNews(account,
				createDate(2014, 01, 01, 10, 10, 1), gainedAchievement);
		challenge1v1StartedNews = createChallenge1v1StartedNews(account,
				createDate(2014, 01, 01, 10, 10, 3), challenge1v1);
		challenge1v1DeclinedNews = createChallenge1v1DeclinedNews(account,
				createDate(2014, 01, 01, 10, 10, 4), challenge1v1);
		newKegNews = createNewKegNews(account,
				createDate(2014, 01, 01, 10, 10, 5), keg);
		newUserNews = createNewUserNews(account,
				createDate(2014, 01, 01, 10, 10, 6), user);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(any(String.class)))
				.thenReturn(account);
		mockAuthService(authService);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsUnfiltered() {
		Response result = new NewsResource()
				.retrieveNews(0, 100, null, "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(6, news.size());

		NewsAdapter adapter = new NewsAdapter();

		// latest news first!
		assertEquals(adapter.adapt(drawingNews), news.get(5));
		assertEquals(adapter.adapt(achievementNews), news.get(4));
		assertEquals(adapter.adapt(challenge1v1StartedNews), news.get(3));
		assertEquals(adapter.adapt(challenge1v1DeclinedNews), news.get(2));
		assertEquals(adapter.adapt(newKegNews), news.get(1));
		assertEquals(adapter.adapt(newUserNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsDrawingNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100,
				"DrawingNews", "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(drawingNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsAchievementNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100,
				"AchievementNews", "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(achievementNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsChallenge1v1StartedNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100,
				"Challenge1v1StartedNews", "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(challenge1v1StartedNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsChallenge1v1DeclinedNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100,
				"Challenge1v1DeclinedNews", "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(challenge1v1DeclinedNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsNewKegNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100, "NewKegNews",
				"token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(newKegNews), news.get(0));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRetrieveNewsNewUserNewsFilter() {
		Response result = new NewsResource().retrieveNews(0, 100,
				"NewUserNews", "token");

		assertEquals(Status.OK.getStatusCode(), result.getStatus());

		List<AbstractNewsResponse> news = (List<AbstractNewsResponse>) result
				.getEntity();
		assertEquals(1, news.size());

		NewsAdapter adapter = new NewsAdapter();
		assertEquals(adapter.adapt(newUserNews), news.get(0));
	}

}

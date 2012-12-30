package de.kile.zapfmaster2000.rest.core.news;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.achievement.AchievementService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.box.DrawService;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeService;
import de.kile.zapfmaster2000.rest.core.keg.KegService;
import de.kile.zapfmaster2000.rest.impl.core.news.NewsServiceImpl;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestNewsService extends AbstractDatabaseTest {

	private Keg keg;
	private User user;
	private Box box;
	private Account account;
	private Drawing drawing;

	@Before
	public void setupData() {
		user = Zapfmaster2000Factory.eINSTANCE.createUser();
		user.setImagePath("img/user1");

		box = Zapfmaster2000Factory.eINSTANCE.createBox();

		keg = Zapfmaster2000Factory.eINSTANCE.createKeg();
		keg.setBox(box);

		account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		account.getBoxes().add(box);

		drawing = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing.setDate(new Date());
		drawing.setAmount(0.123);
		drawing.setUser(user);
		drawing.setKeg(keg);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(keg);
		session.save(user);
		session.save(box);
		session.save(account);
		session.save(drawing);

		tx.commit();
	}

	@Test
	public void simpleEndDraw() {
		BoxServiceMock boxServiceMock = new BoxServiceMock();
		new NewsServiceImpl(boxServiceMock, mock(AchievementService.class),
				mock(ChallengeService.class), mock(KegService.class));

		for (BoxServiceListener listener : boxServiceMock.listeners) {
			listener.onEndDrawing(box, drawing);
		}

		// check db if news was actually added
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<News> allNews = session.createQuery("FROM News").list();
		assertEquals(1, allNews.size());

		DrawingNews news = (DrawingNews) allNews.get(0);
		assertEquals(drawing.getId(), news.getDrawing().getId());

		tx.commit();

	}

	private class BoxServiceMock implements BoxService {

		public final List<BoxServiceListener> listeners = new ArrayList<>();

		@Override
		public DrawService getDrawService(String pBoxPassphrase)
				throws IllegalArgumentException {
			return null;
		}

		@Override
		public void addListener(BoxServiceListener pListener) {
			listeners.add(pListener);
		}

		@Override
		public void removeListener(BoxServiceListener pListener) {
		}

	}

}

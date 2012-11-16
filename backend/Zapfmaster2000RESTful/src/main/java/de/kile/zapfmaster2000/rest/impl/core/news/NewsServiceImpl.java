package de.kile.zapfmaster2000.rest.impl.core.news;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.NewsType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class NewsServiceImpl implements NewsService {

	/** logger */
	private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

	public NewsServiceImpl(BoxService pBoxService) {
		pBoxService.addListener(new BoxServiceListener() {

			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				postNewsDrawFinished(pBox, pDrawing);
			}
		});
	}

	@Override
	public void addListener(NewsServiceListener pListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(NewsServiceListener pListener) {
		// TODO Auto-generated method stub

	}

	private void postNewsDrawFinished(Box pBox, Drawing pDrawing) {
		LOG.debug("Posting news (draw finished): " + pDrawing.getId());

		// find system for the given box
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Account> accounts = session
				.createQuery("SELECT b.account FROM Box b WHERE b.id = :id")
				.setLong("id", pBox.getId()).list();

		if (accounts.size() == 1) {
			News news = Zapfmaster2000Factory.eINSTANCE.createNews();
			news.setAccount(accounts.get(0));
			news.setContents(String.valueOf(pDrawing.getId()));
			news.setImagePath(pDrawing.getUser().getImagePath());
			news.setDate(new Date());
			news.setType(NewsType.DRAWING);
			session.save(news);
		} else {
			LOG.error("Could not post news: No (unique) system found for box "
					+ pBox.getId());
		}

		tx.commit();
	}
}

package de.beeromat.internal.core.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.draw.DrawManagerAdapter;
import de.beeromat.core.draw.DrawManagerListener;
import de.beeromat.core.model.db.Drawing;
import de.beeromat.core.model.db.Keg;
import de.beeromat.core.model.db.News;
import de.beeromat.core.model.db.User;
import de.beeromat.core.request.RequestExecutor;
import de.beeromat.core.request.RequestType;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

/**
 * Handles drawing events. Updates the database when a drawing was finished.
 * Will also send update messages to the backend.php as post request.
 * 
 * @author thomas
 */
public class DrawingsHandler {

	private static final Logger LOG = Logger.getLogger(DrawingsHandler.class);

	private boolean fDrawingStarted = false;

	private Date fStartTime;

	private Date fLastDraw;

	private final List<DrawingsHandlerListener> fListeners = new ArrayList<DrawingsHandlerListener>();

	public DrawingsHandler(DrawManager pManager) {
		Assert.isNotNull(pManager);
		pManager.addDrawManagerListener(createListener());
	}

	public void addListener(DrawingsHandlerListener pListener) {
		fListeners.add(pListener);
	}

	public void removeListener(DrawingsHandlerListener pListener) {
		fListeners.remove(pListener);
	}

	private DrawManagerListener createListener() {
		return new DrawManagerAdapter() {

			@Override
			public void onLoginsuccessful(User pUser) {
				fDrawingStarted = false;
			}

			@Override
			public void onDrawing(User pUser, double pAmount) {
				doPerformDrawing(pUser, pAmount, true);
			}

			@Override
			public void onEndDrawing(User pUser, double pAmount) {
				doPerformEndDrawing(pUser, pAmount);
			}

		};
	}

	private void doPerformDrawing(User pUser, double pAmount, boolean updateDiff) {
		Assert.isNotNull(pUser);

		RequestExecutor executor = BeeromatCoreActivator.getDefault().getCore()
				.getRequestExecutor();
		PostMethod method = executor.createPostMethod(RequestType.DRAWING);

		String kind;
		if (fDrawingStarted) {
			kind = "refresh";
		} else {
			kind = "new";
			fDrawingStarted = true;
			fStartTime = new Date();
		}
		if (updateDiff) {
			fLastDraw = new Date();
		}
		long diff = (fLastDraw.getTime() - fStartTime.getTime()) / 1000;

		method.setParameter("kind", kind);
		method.setParameter("user", String.valueOf(pUser.getUserId()));
		method.setParameter("amount", String.valueOf(pAmount));
		method.setParameter("duration", String.valueOf(diff));

		executor.executePost(method);
	}

	private void doPerformEndDrawing(User pUser, double pAmount) {
		Assert.isNotNull(pUser);
		if (pAmount > 0 && fStartTime != null) {
			long diff = (fLastDraw.getTime() - fStartTime.getTime()) / 1000;

			// last update
			doPerformDrawing(pUser, pAmount, false);

			Keg keg = BeeromatCoreActivator.getDefault().getCore()
					.getKegManager().readCurrentKeg();

			// update Db
			SessionFactory factory = SessionFactoryUtil.getInstance();
			Session session = factory.openSession();

			Drawing drawing = new Drawing();
			drawing.setUserId(pUser.getUserId());
			drawing.setDate(new Date());
			drawing.setDuration(diff);
			drawing.setRealAmount(pAmount);
			drawing.setRawAmount(0); // TODO
			drawing.setKegId(keg.getKegId());

			News news = new News();
			news.setImagePath(pUser.getImagePath());
			news.setNewsType("DRAWING");

			Transaction transaction = session.beginTransaction();
			session.save(drawing);
			news.setContents(String.valueOf(drawing.getDrawingId()));
			session.save(news);
			transaction.commit();

			session.close();

			nottifyListenersDrawFinished(pUser);
		}
	}

	private void nottifyListenersDrawFinished(User pUser) {
		for (DrawingsHandlerListener listener : fListeners) {
			try {
				listener.onDrawingFinished(pUser);
			} catch (Throwable th) {
				LOG.error(
						"Error while processing drawing handler on drawing finished event",
						th);
			}
		}
	}

}
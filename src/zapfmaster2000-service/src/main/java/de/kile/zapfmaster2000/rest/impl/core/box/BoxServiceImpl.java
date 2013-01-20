package de.kile.zapfmaster2000.rest.impl.core.box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.box.DrawService;
import de.kile.zapfmaster2000.rest.core.box.DrawServiceListener;
import de.kile.zapfmaster2000.rest.core.box.LoginFailureReason;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class BoxServiceImpl implements BoxService {

	private List<BoxServiceListener> boxServiceListeners = new ArrayList<>();

	private Map<String, DrawService> mapBox2DrawService = new HashMap<>();

	@Override
	public DrawService getDrawService(String pBoxPassphrase)
			throws IllegalArgumentException {

		// return cached draw manager if possible
		if (mapBox2DrawService.containsKey(pBoxPassphrase)) {
			return mapBox2DrawService.get(pBoxPassphrase);
		}

		// query database for box manager otherwise
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Box> box = session
					.createQuery("FROM Box b WHERE b.passphrase = :passphrase")
					.setString("passphrase", pBoxPassphrase).list();
			if (box.size() != 1) {
				throw new IllegalArgumentException("Box with passphrase \""
						+ pBoxPassphrase + "\" does not exist");
			}
			DrawService drawService = new DrawServiceImpl(box.get(0));
			drawService.addListener(createDrawServiceListener(drawService));
			mapBox2DrawService.put(pBoxPassphrase, drawService);
			return drawService;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public void addListener(BoxServiceListener pListener) {
		if (pListener != null) {
			boxServiceListeners.add(pListener);
		}

	}

	@Override
	public void removeListener(BoxServiceListener pListener) {
		boxServiceListeners.remove(pListener);
	}

	private DrawServiceListener createDrawServiceListener(
			final DrawService pService) {
		return new DrawServiceListener() {

			private final Box box = pService.getBox();

			@Override
			public void onLoginsuccessful(User pUser) {
				for (BoxServiceListener listener : boxServiceListeners) {
					listener.onLoginsuccessful(box, pUser);
				}
			}

			@Override
			public void onEndDrawing(Drawing pDrawing) {
				for (BoxServiceListener listener : boxServiceListeners) {
					listener.onEndDrawing(box, pDrawing);
				}
			}

			@Override
			public void onDrawing(User pUser, double pAmount) {
				for (BoxServiceListener listener : boxServiceListeners) {
					listener.onDrawing(box, pUser, pAmount);
				}

			}

			@Override
			public void onLogout(User pUser) {
				for (BoxServiceListener listener : boxServiceListeners) {
					listener.onLogout(box, pUser);
				}				
			}

			@Override
			public void onLoginFailed(LoginFailureReason pReason, long pTag) {
				for (BoxServiceListener listener : boxServiceListeners) {
					listener.onLoginFailed(box, pReason, pTag);
				}		
			}
		};
	}

}

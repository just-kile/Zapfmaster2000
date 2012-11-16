package de.kile.zapfmaster2000.rest.impl.core.box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.DrawService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

public class BoxServiceImpl implements BoxService {

	private Map<String, DrawService> mapBox2DrawManager = new HashMap<>();

	@Override
	public DrawService getDrawService(String pBoxPassphrase)
			throws IllegalArgumentException {

		// return cached draw manager if possible
		if (mapBox2DrawManager.containsKey(pBoxPassphrase)) {
			return mapBox2DrawManager.get(pBoxPassphrase);
		}

		// query database for box manager otherwisse
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<Box> box = session
					.createQuery(
							"FROM Box b WHERE b.passphrase = :passphrase")
					.setString("passphrase", pBoxPassphrase).list();
			if (box.size() != 1) {
				throw new IllegalArgumentException("Box with passphrase \""
						+ pBoxPassphrase + "\" does not exist");
			}
			DrawService drawManager = new DrawServiceImpl(box.get(0));
			mapBox2DrawManager.put(pBoxPassphrase, drawManager);
			return drawManager;
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}
}

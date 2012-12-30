package de.kile.zapfmaster2000.rest.impl.core.keg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.keg.KegService;
import de.kile.zapfmaster2000.rest.core.keg.KegServiceListener;
import de.kile.zapfmaster2000.rest.impl.core.transaction.SharedQueries;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class KegServiceImpl implements KegService {

	private final List<KegServiceListener> listeners = new ArrayList<>();

	@Override
	public void switchKeg(Box pBox, String pBrand, int pSize) {
		Keg activeKeg = SharedQueries.retrieveActiveKeg(pBox);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		pBox = (Box) session.load(Zapfmaster2000Package.eINSTANCE.getBox()
				.getName(), pBox.getId());

		// set end date for active keg
		if (activeKeg != null) {
			activeKeg = (Keg) session.load(Zapfmaster2000Package.eINSTANCE
					.getKeg().getName(), activeKeg.getId());
			activeKeg.setEndDate(new Date());
			session.update(activeKeg);
		}
		
		// add new keg
		Keg newKeg = Zapfmaster2000Factory.eINSTANCE.createKeg();
		newKeg.setBox(pBox);
		newKeg.setBrand(pBrand);
		newKeg.setSize(pSize);
		newKeg.setStartDate(new Date());
		session.save(newKeg);

		tx.commit();

		notifyNewKeg(newKeg);
	}

	@Override
	public void addListener(KegServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(KegServiceListener pListener) {
		if (pListener != null) {
			listeners.remove(pListener);
		}
	}

	private void notifyNewKeg(Keg pKeg) {
		for (KegServiceListener listener : listeners) {
			listener.onNewKeg(pKeg);
		}
	}

}

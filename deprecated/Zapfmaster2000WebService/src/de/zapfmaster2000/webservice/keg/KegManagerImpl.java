package de.zapfmaster2000.webservice.keg;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.Keg;

public class KegManagerImpl implements KegManager {

	@Override
	public Keg readCurrentKeg() {
		Transaction tx = null;
		Keg keg = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		try {
			tx = session.beginTransaction();
			List<?> kegs = session.createQuery(
					"select k from Keg as k order by kegId desc").list();

			if (kegs.size() > 0) {
				keg = (Keg) kegs.get(0);
			}
			tx.commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
				}
				// throw again the first exception
				throw ex;
			}

		}
		return keg;
	}

	@Override
	public void swapKeq(Keg pNewKeg) {
		throw new UnsupportedOperationException("not implemented yet");

	}

}

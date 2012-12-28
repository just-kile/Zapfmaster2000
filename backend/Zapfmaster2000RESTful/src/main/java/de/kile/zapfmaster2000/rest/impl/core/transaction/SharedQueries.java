package de.kile.zapfmaster2000.rest.impl.core.transaction;

import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;

/**
 * Shared queries.
 * 
 *  <p>
 *  TODO: This class is ugly. Use DAOs?
 *  </p>
 * 
 * @author Thomas Kipar
 */
public final class SharedQueries {

	private SharedQueries() {
	}

	/**
	 * Returns the active keg for the given box.
	 * 
	 * @param pBox
	 *            box, must not be <code>null</code>.
	 * @return the active keg for the given box.
	 */
	public static Keg retrieveActiveKeg(Box pBox) {
		Keg keg = null;
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			List<Keg> kegs = session
					.createQuery(
							"FROM Keg k WHERE k.box.id = :boxId ORDER BY k.startDate DESC")
					.setLong("boxId", pBox.getId()).list();
			if (kegs.isEmpty()) {
				// problem over here
				throw new WebServiceException("Box " + pBox.getId()
						+ " has no active keg.");
			}
			keg = kegs.get(0); // active keg
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
			throw ex;
		}
		return keg;
	}
	
	public static double retrieveDrawingAmount(long pUserId, Date pFrom, Date pTo) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Double> result = session.createQuery("SELECT sum(d.amount) FROM Drawing d " +
				"WHERE d.user.id = :userId AND d.date >= :from AND d.date <= :to " +
				"GROUP BY d.user.id").list();
		tx.commit();
		
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return 0;
	}

}

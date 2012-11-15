package de.kile.zapfmaster2000.rest;

import org.hibernate.Transaction;
import org.junit.After;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

/**
 * Base class for all unit tests that access the database.
 * 
 * <p>
 * Will truncate the whole db after each test.
 * </p>
 * 
 * 
 * @author Thomas Kipar
 */
public abstract class AbstractDatabaseTest {

	@After
	public void truncate() {
		Transaction tx = Zapfmaster2000Core.INSTANCE.getTransactionManager()
				.getSessionFactory().getCurrentSession().beginTransaction();
		Zapfmaster2000Core.INSTANCE.getTransactionManager().getSessionFactory()
				.getCurrentSession()
				.createSQLQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT")
				.executeUpdate();

		tx.commit();
	}
}

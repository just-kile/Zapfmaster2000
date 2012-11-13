package de.kile.zapfmaster2000.rest.core;

import org.hibernate.SessionFactory;

/**
 * The transaction manager provides access to the data base using hibernate.
 * 
 * @author Thomas Kipar
 */
public interface TransactionManager {
	
	/**
	 * Returns the session factory that can be used to create data base transactions.
	 * 
	 * @return the session factory. Never <code>null</code>.
	 */
	public SessionFactory getSessionFactory();

}

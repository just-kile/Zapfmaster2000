package de.kile.zapfmaster2000.rest.impl.core;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class TransactionManagerImpl implements TransactionManager {

	/** session factory */
	private final SessionFactory sessionFactory;

	/**
	 * Constructor
	 */
	public TransactionManagerImpl() {
		sessionFactory = configureHibernate();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Configures hibernate.
	 * 
	 * @return the session factory to connection to hibernate. Never
	 *         <code>null</code>.
	 */
	private SessionFactory configureHibernate() {
		// Create the DataStore.
		
		Configuration cfg = new Configuration().configure();
		final String dataStoreName = "Zapfmaster2000DataStore";
		final HbDataStore dataStore = HbHelper.INSTANCE
				.createRegisterDataStore(dataStoreName);
		dataStore.setDataStoreProperties(cfg.getProperties());
		dataStore
				.setEPackages(new EPackage[] { Zapfmaster2000Package.eINSTANCE });
		dataStore.initialize();

		return dataStore.getSessionFactory();
	}
}

package de.kile.zapfmaster2000.rest.impl.core;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class TransationManagerImpl implements TransactionManager {

	/** session factory */
	private final SessionFactory sessionFactory;

	/**
	 * Constructor
	 */
	TransationManagerImpl() {
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
		// TODO: extract configuration from code
		Properties hibernateProperties = new Properties();

		String dbName = "zapfmaster2000";
		hibernateProperties.setProperty(Environment.DRIVER,
				"com.mysql.jdbc.Driver");
		hibernateProperties.setProperty(Environment.USER, "root");
		hibernateProperties.setProperty(Environment.URL,
				"jdbc:mysql://127.0.0.1:3306/" + dbName);
		hibernateProperties.setProperty(Environment.PASS, "foo");
		hibernateProperties.setProperty(Environment.DIALECT,
				org.hibernate.dialect.MySQLDialect.class.getName());
		hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "update");

		hibernateProperties.setProperty(
				PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT,
				"REFRESH,PERSIST,MERGE");
		hibernateProperties.setProperty(PersistenceOptions.INHERITANCE_MAPPING,
				"JOINED");

		// Create the DataStore.
		final String dataStoreName = "LibraryDataStore";
		final HbDataStore dataStore = HbHelper.INSTANCE
				.createRegisterDataStore(dataStoreName);
		dataStore.setDataStoreProperties(hibernateProperties);
		dataStore
				.setEPackages(new EPackage[] { Zapfmaster2000Package.eINSTANCE });
		dataStore.initialize();

		return dataStore.getSessionFactory();
	}
}

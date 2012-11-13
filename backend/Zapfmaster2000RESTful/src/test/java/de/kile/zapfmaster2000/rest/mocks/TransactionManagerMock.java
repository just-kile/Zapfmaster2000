package de.kile.zapfmaster2000.rest.mocks;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

/**
 * Transaction manager mock that uses hqldb as backent. Whenever the manager is
 * created once more, all data is dropped from previous db.
 * 
 * @author Thomas Kipar
 */
public class TransactionManagerMock implements TransactionManager {

	private final SessionFactory sessionFactory;

	public TransactionManagerMock() {
		sessionFactory = createSessionFactory();
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private SessionFactory createSessionFactory() {

		final Properties props = new Properties();
		props.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
		props.setProperty(Environment.USER, "sa");
		props.setProperty(Environment.URL, "jdbc:hsqldb:mem:library");
		props.setProperty(Environment.PASS, "");
		props.setProperty(Environment.DIALECT,
				org.hibernate.dialect.HSQLDialect.class.getName());
		props.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
		props.setProperty(PersistenceOptions.CASCADE_POLICY_ON_NON_CONTAINMENT,
				"REFRESH,PERSIST,MERGE");

		String hbName = "zm2k-unittest";
		final HbDataStore hbds = HbHelper.INSTANCE
				.createRegisterDataStore(hbName);
		hbds.setDataStoreProperties(props);

		hbds.setEPackages(new EPackage[] { Zapfmaster2000Package.eINSTANCE });
		hbds.initialize();

		return hbds.getSessionFactory();

	}
}

package de.zapfmaster2000.webservice.db;

/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 22, 2006
 * copyright 2006 by http://www.laliluna.de
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import de.zapfmaster2000.webservice.model.db.Achievement;
import de.zapfmaster2000.webservice.model.db.Challenge;
import de.zapfmaster2000.webservice.model.db.Challenge1v1;
import de.zapfmaster2000.webservice.model.db.ChallengeParticipant;
import de.zapfmaster2000.webservice.model.db.Config;
import de.zapfmaster2000.webservice.model.db.Drawing;
import de.zapfmaster2000.webservice.model.db.GainedAchievement;
import de.zapfmaster2000.webservice.model.db.Keg;
import de.zapfmaster2000.webservice.model.db.News;
import de.zapfmaster2000.webservice.model.db.User;

/**
 * @author hennebrueder This class garanties that only one single SessionFactory
 *         is instanciated and that the configuration is done thread safe as
 *         singleton. Actually it only wraps the Hibernate SessionFactory. You
 *         are free to use any kind of JTA or Thread transactionFactories.
 */
public class SessionFactoryUtil {

	/** The single instance of hibernate SessionFactory */
	private static org.hibernate.SessionFactory sessionFactory;

	/**
	 * disable contructor to guaranty a single instance
	 */
	private SessionFactoryUtil() {
	}

	static {

		try {
			Configuration cfg = new AnnotationConfiguration()
					.addAnnotatedClass(Achievement.class)
					.addAnnotatedClass(Challenge.class)
					.addAnnotatedClass(Challenge1v1.class)
					.addAnnotatedClass(ChallengeParticipant.class)
					.addAnnotatedClass(Config.class)
					.addAnnotatedClass(Drawing.class)
					.addAnnotatedClass(GainedAchievement.class)
					.addAnnotatedClass(Keg.class)
					.addAnnotatedClass(News.class)
					.addAnnotatedClass(User.class)
					.setProperty("hibernate.connection.url",
							"jdbc:mysql://server/SCHEMA_BEEROMAT")
					.setProperty("hibernate.connection.username", "bier")
					.setProperty("hibernate.connection.password", "start123")
					.setProperty("hibernate.connection.driver_class",
							"com.mysql.jdbc.Driver")
					.setProperty("hibernate.dialect",
							"org.hibernate.dialect.MySQLDialect")
					.setProperty("hibernate.transaction.factory_class",
							"org.hibernate.transaction.JDBCTransactionFactory")
					.setProperty("hibernate.current_session_context_class", "thread")
					.setProperty("hibernate.show_sql", "false");

			sessionFactory = cfg.buildSessionFactory();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	/**
	 * Opens a session and will not bind it to a session context
	 * 
	 * @return the session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Returns a session from the session context. If there is no session in the
	 * context it opens a session, stores it in the context and returns it. This
	 * factory is intended to be used with a hibernate.cfg.xml including the
	 * following property <property
	 * name="current_session_context_class">thread</property> This would return
	 * the current open session or if this does not exist, will create a new
	 * session
	 * 
	 * @return the session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * closes the session factory
	 */
	public static void close() {
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;

	}
}
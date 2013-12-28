package de.kile.zapfmaster2000.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

/**
 * Base class for all unit tests that access the database.
 * 
 * <p>
 * Will truncate the whole db after each test.
 * </p>
 * 
 * @author Thomas Kipar
 */
public abstract class AbstractDatabaseTest {

	@After
	public void closeTransactions() {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();

		if (transaction != null && transaction.isActive()) {
			transaction.rollback();
		}
	}

	@After
	public void truncate() {
		SessionFactory sessionFactory = Zapfmaster2000Core.INSTANCE
				.getTransactionService().getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.createSQLQuery("SET REFERENTIAL_INTEGRITY FALSE")
				.executeUpdate();

		@SuppressWarnings("unchecked")
		List<Object[]> result = session.createSQLQuery(
				"SHOW TABLES FROM PUBLIC").list();
		for (Object[] tableDesc : result) {
			final int indexTableName = 0;
			String tableName = (String) tableDesc[indexTableName];
			session.createSQLQuery("TRUNCATE TABLE " + tableName)
					.executeUpdate();
		}

		session.createSQLQuery("SET REFERENTIAL_INTEGRITY TRUE")
				.executeUpdate();

		tx.commit();
	}

	protected Account createAccount(String pName) {
		Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		account.setName(pName);
		saveEntity(account);
		return account;
	}

	protected Box createBox(String pPassphrase, String pLocation,
			String pVersion, Account pAccount) {
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setAccount(pAccount);
		box.setLocation(pLocation);
		box.setPassphrase(pPassphrase);
		box.setVersion(pVersion);
		saveEntity(box, pAccount);
		return box;
	}

	protected Box createBox(String pPassphrase, String pLocation,
			String pVersion, double pA2, double pA1, int pA0, Account pAccount) {
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setAccount(pAccount);
		box.setLocation(pLocation);
		box.setPassphrase(pPassphrase);
		box.setVersion(pVersion);
		box.setA2(pA2);
		box.setA1(pA1);
		box.setA0(pA0);
		saveEntity(box, pAccount);
		return box;
	}

	protected User createUser(String pName, String pImagePath,
			String pPassword, long pRfidTag, Sex pSex, int pWeight,
			UserType pUserType, Account pAccount) {
		User user = Zapfmaster2000Factory.eINSTANCE.createUser();
		user.setName(pName);
		user.setImagePath(pImagePath);
		user.setPassword(pPassword);
		user.setRfidTag(pRfidTag);
		user.setSex(pSex);
		user.setType(pUserType);
		user.setWeight(pWeight);
		user.setAccount(pAccount);
		saveEntity(user, pAccount);
		return user;
	}

	protected Keg createKeg(String pBrand, Date pStartDate, Date pEndDate,
			int pSize, Box pBox) {
		Keg keg = Zapfmaster2000Factory.eINSTANCE.createKeg();
		keg.setBrand(pBrand);
		keg.setStartDate(pStartDate);
		keg.setEndDate(pEndDate);
		keg.setSize(pSize);
		keg.setBox(pBox);
		saveEntity(keg, pBox);
		return keg;
	}

	protected Drawing createDrawing(double pAmount, Date pDate, Keg pKeg,
			User pUser) {
		Drawing drawing = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing.setAmount(pAmount);
		drawing.setDate(pDate);
		drawing.setKeg(pKeg);
		drawing.setUser(pUser);
		saveEntity(drawing, pUser, pKeg);
		return drawing;
	}

	protected Achievement createAchievement(String pName, String pDescription,
			String pImagePath) {
		Achievement achievement = Zapfmaster2000Factory.eINSTANCE
				.createAchievement();
		achievement.setName(pName);
		achievement.setDescription(pDescription);
		achievement.setImagePath(pImagePath);
		saveEntity(achievement);
		return achievement;
	}

	protected GainedAchievement createGainedAchievement(Date pDate, User pUser,
			Achievement pAchievement) {
		GainedAchievement gainedAchievement = Zapfmaster2000Factory.eINSTANCE
				.createGainedAchievement();
		gainedAchievement.setDate(pDate);
		gainedAchievement.setUser(pUser);
		gainedAchievement.setAchievement(pAchievement);
		saveEntity(gainedAchievement, pUser, pAchievement);
		return gainedAchievement;
	}

	protected Date createDate(int pYear, int pMonth, int pDay) {
		Calendar cal = Calendar.getInstance();
		cal.set(pYear, pMonth, pDay);
		return cal.getTime();
	}

	protected Date createDate(int pYear, int pMonth, int pDay, int pHour,
			int pMinute, int pSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(pYear, pMonth, pDay, pHour, pMinute, pSecond);
		return cal.getTime();
	}

	protected Admin createAdmin(String name, String password, Account account) {
		Admin admin = Zapfmaster2000Factory.eINSTANCE.createAdmin();
		admin.setName(name);
		admin.setPassword(password);
		admin.setAccount(account);
		saveEntity(admin);
		return admin;
	}

	protected Token createToken(String token, User user, Account account,
			Admin admin, String gcmToken) {
		Token dbToken = Zapfmaster2000Factory.eINSTANCE.createToken();
		dbToken.setToken(token);
		dbToken.setAdmin(admin);
		dbToken.setAccount(account);
		dbToken.setUser(user);
		dbToken.setGoogleCloudMessagingToken(gcmToken);
		saveEntity(dbToken);
		return dbToken;
	}

	protected void saveEntity(EObject pEntity, EObject... pEntitiesToUpdate) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (EObject entityToUpdate : pEntitiesToUpdate) {
			session.update(entityToUpdate);
		}
		session.save(pEntity);
		tx.commit();
	}
}

package de.kile.zapfmaster2000.test;

import java.util.Date;

import org.hibernate.Session;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class EntityCreator {
	
	public static void main(String[] pArgs) {
		Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		account.setName("account-1");
		
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setVersion("1.0");
		box.setPassphrase("box-1");
		box.setAccount(account);
		
		Keg keg = Zapfmaster2000Factory.eINSTANCE.createKeg();
		keg.setBox(box);
		keg.setBrand("keg-brand-1");
		keg.setStartDate(new Date());
		keg.setSize(50);
		
		User user1 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("user-1");
		user1.setRfidTag(100);
		user1.setImagePath("img/user1.png");
		user1.setPassword("pw1");
		user1.setSex(Sex.MALE);
		user1.setType(UserType.USER);
		user1.setWeight(75);
		user1.setAccount(account);

		User user2 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user2.setName("user-2");
		user2.setRfidTag(200);
		user2.setImagePath("img/user2.png");
		user2.setPassword("pw2");
		user2.setSex(Sex.MALE);
		user2.setType(UserType.USER);
		user2.setWeight(75);
		user2.setAccount(account);
		
		User user3 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user3.setName("user-3");
		user3.setRfidTag(300);
		user3.setImagePath("img/user3.png");
		user3.setPassword("pw3");
		user3.setSex(Sex.FEMALE);
		user3.setType(UserType.USER);
		user3.setWeight(75);
		user3.setAccount(account);
		
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(account);
		session.save(box);
		session.save(keg);
		session.save(user1);
		session.save(user2);
		session.save(user3);
		
		
		session.getTransaction().commit();
		
		System.out.println("done.");
	}

}

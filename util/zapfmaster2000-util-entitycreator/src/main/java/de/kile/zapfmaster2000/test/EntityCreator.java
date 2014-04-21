package de.kile.zapfmaster2000.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.imgscalr.Scalr;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.AdminType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class EntityCreator {

	private static final int IMAGE_SIZE = 48;

	private static final int IMAGE_SIZE_BIG = 160;

	private static HttpClient httpclient = new DefaultHttpClient();

	public static void main(String[] pArgs) throws IOException {

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		int numAccounts = 1;
		int numUsersPerAccount = 30;
		int rfidOffset = 1000;
		int numBoxesPerAccount = 3;

		Admin globalAdmin = Zapfmaster2000Factory.eINSTANCE.createAdmin();
		globalAdmin.setName("admin");
		globalAdmin.setPassword("password");
		globalAdmin.setType(AdminType.GLOBAL);
		session.save(globalAdmin);

		for (int i = 0; i < numAccounts; i++) {

			Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
			account.setName("account-" + i);

			Admin accountAdmin = Zapfmaster2000Factory.eINSTANCE.createAdmin();
			accountAdmin.setName("accountadmin" + i);
			accountAdmin.setPassword("password");
			accountAdmin.setType(AdminType.ACCOUNT);
			accountAdmin.setAccount(account);
			session.save(accountAdmin);

			for (int j = 0; j < numBoxesPerAccount; j++) {
				int boxId = (numBoxesPerAccount * i) + j;
				Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
				box.setVersion("1.0");
				box.setPassphrase("box-" + boxId);
				box.setAccount(account);
				box.setLocation("box-" + boxId);

				box.setA0(0.00006186472614225462);
				box.setA1(0.0000825562566780224);
				box.setA2(-1.675383403699784e-8);

				Keg keg = Zapfmaster2000Factory.eINSTANCE.createKeg();
				keg.setBox(box);
				keg.setBrand("Berliner Pilsner");
				keg.setStartDate(new Date());
				keg.setSize(50);

				account.getBoxes().add(box);

				session.save(keg);
				session.save(box);
			}

			for (int j = 0; j < numUsersPerAccount; j++) {
				int userId = i * numUsersPerAccount + j;
				User user = Zapfmaster2000Factory.eINSTANCE.createUser();
				user.setName("user-" + userId);
				user.setRfidTag(rfidOffset + userId);
				user.setImagePath("img/guest.png");
				user.setPassword("pw" + userId);
				user.setSex(Sex.MALE);
				user.setType(UserType.USER);
				user.setWeight(75);
				user.setAccount(account);

				session.save(user);
				
				createUserImage(user, session);
			}

			session.save(account);
		}

		session.getTransaction().commit();

		System.out.println("done.");
	}

	public static void createUserImage(User user, Session session)
			throws IOException {
		
		String url = "http://lorempixel.com/196/196/cats/";
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = (CloseableHttpResponse) httpclient
				.execute(httpGet);

		HttpEntity entitiy = response.getEntity();

		InputStream inputStream = entitiy.getContent();

		// scale image
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		double ratio = bufferedImage.getWidth()
				/ (double) bufferedImage.getHeight();
		if (ratio > 1) {
			int newWidth = bufferedImage.getHeight();
			int pixelsToCrop = (bufferedImage.getWidth() - newWidth) / 2;
			bufferedImage = Scalr.crop(bufferedImage, pixelsToCrop, 0,
					newWidth, bufferedImage.getHeight());
		} else if (ratio < 1) {
			int newHeight = bufferedImage.getWidth();
			int pixelsToCrop = (bufferedImage.getHeight() - newHeight) / 2;
			bufferedImage = Scalr.crop(bufferedImage, 0, pixelsToCrop,
					bufferedImage.getWidth(), newHeight);
		}

		BufferedImage imageSmall = Scalr.resize(bufferedImage,
				Scalr.Mode.FIT_EXACT, IMAGE_SIZE);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(imageSmall, "png", outputStream);
		byte[] bytesSmall = outputStream.toByteArray();

		BufferedImage imageBig = Scalr.resize(bufferedImage,
				Scalr.Mode.FIT_EXACT, IMAGE_SIZE_BIG);
		outputStream = new ByteArrayOutputStream();
		ImageIO.write(imageBig, "png", outputStream);
		byte[] bytesBig = outputStream.toByteArray();

		// write data to db
		String path = "rest/image/user/" + user.getId();

		// create new image
		Image newImage = Zapfmaster2000Factory.eINSTANCE.createImage();
		newImage.setPath(path);
		newImage.setContentType("image/png");
		newImage.setContent(Hibernate.getLobCreator(session).createBlob(
				bytesSmall));
		newImage.setContentBig(Hibernate.getLobCreator(session).createBlob(
				bytesBig));

		session.save(newImage);
		user.setImagePath(path);
		session.save(user);

		EntityUtils.consume(entitiy);
		httpGet.releaseConnection();
	}

}

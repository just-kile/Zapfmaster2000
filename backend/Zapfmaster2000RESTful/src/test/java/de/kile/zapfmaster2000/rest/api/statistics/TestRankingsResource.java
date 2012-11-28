package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link RankingsResource}.
 * Different users, multiple {@link Drawing}, multiple {@link Account}
 * 
 * @author PB
 *
 */
public class TestRankingsResource extends AbstractMockingTest {

	private Account account;
	private Account account2;
	
	private User user1, user2, user3, userForeignAcc, userEmpty;
	private Drawing drawing1, drawing2, drawing3, drawing4, drawing5;  
	

	@Before
	public void setupData() {
		account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		account2 = Zapfmaster2000Factory.eINSTANCE.createAccount();

		//test users
		//number2 in ranking
		user1 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("Horst");
		user1.setImagePath("/imagePath/image.jpg");

		user2 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user2.setName("Ingrid");
		
		//number1 in ranking
		user3 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user3.setName("Waldemar");
		
		//user in different account		
		userForeignAcc = Zapfmaster2000Factory.eINSTANCE.createUser();
		userForeignAcc.setName("Judas");

		//no drawings
		userEmpty = Zapfmaster2000Factory.eINSTANCE.createUser();
		userEmpty.setName("Wilfried");
		
		SimpleDateFormat df = new SimpleDateFormat(PlatformConstants.DATE_TIME_FORMAT);
		
		//test drawings
		try {
			drawing1 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
			drawing1.setUser(user1);
			drawing1.setAmount(5.14);
			drawing1.setDate(df.parse("20120101-120000"));
			
			drawing2 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
			drawing2.setUser(user2);
			drawing2.setAmount(2.71);
			drawing2.setDate(df.parse("20120101-130000"));
			
			drawing3 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
			drawing3.setUser(user3);
			drawing3.setAmount(4.1);
			drawing3.setDate(df.parse("20120101-090000"));
			
			drawing4 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
			drawing4.setUser(user3);
			drawing4.setAmount(6);
			drawing4.setDate(df.parse("20120101-140000"));
			
			//drawing in different account. should not appear in ranking 
			drawing5 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
			drawing5.setUser(userForeignAcc);
			drawing5.setAmount(20);
			drawing5.setDate(df.parse("20120101-130000"));
		} catch (ParseException e){
			//
		}
		
		account.getUsers().add(user1);
		account.getUsers().add(user2);
		account.getUsers().add(user3);
		account.getUsers().add(userEmpty);
		account2.getUsers().add(userForeignAcc);
		
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(account);
		session.save(account2);

		tx.commit();

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(null)).thenReturn(account);
		mockAuthService(authService);

	}
	
	@Test
	public void testAmountRanking(){
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource.bestUserListTimeSpan(null,null,null);
		
		Object[] rawUserAmountResponse =  (Object[]) response.getEntity();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		assertEquals(3, rawUserAmountResponse.length);
		
		assertEquals(user3.getId(), ((UserAmountResponse) rawUserAmountResponse[0]).getId());
		assertEquals(user1.getId(),  ((UserAmountResponse) rawUserAmountResponse[1]).getId());
		
		assertEquals(user1.getImagePath(), ((UserAmountResponse) rawUserAmountResponse[1]).getImage());
	}
	
	@Test
	public void testAmountFrom(){
		RankingsResource rankingsResource = new RankingsResource();
		
		String pFrom = "20120101-100000";
		
		Response response = rankingsResource.bestUserListTimeSpan(pFrom,null,null);
		
		Object[] rawUserAmountResponse =  (Object[]) response.getEntity();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		
		assertEquals(3, rawUserAmountResponse.length);
		assertEquals(user3.getId(), ((UserAmountResponse) rawUserAmountResponse[0]).getId());
	}
	
	@Test
	public void testAmountFromTo() {
		RankingsResource rankingsResource = new RankingsResource();
		
		String pFrom = "20120101-100000";
		String pTo = "20120101-130000";
		
		Response response = rankingsResource.bestUserListTimeSpan(pFrom,pTo,null);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		Object[] rawUserAmountResponse =  (Object[]) response.getEntity();
		
		assertEquals(2, rawUserAmountResponse.length);
		//order should have changed
		assertEquals(user1.getId(), ((UserAmountResponse) rawUserAmountResponse[0]).getId());
	}
	
	
	@Test
	public void testDrawCountRanking(){
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource.drawCountUserListTimeSpan(null,null,null);
		
		Object[] rawDrawCountResponse =  (Object[]) response.getEntity();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		assertEquals(3, rawDrawCountResponse.length);
		
		assertEquals(user3.getDrawings().size(), ((DrawCountUserListResponse) rawDrawCountResponse[0]).getDrawCount());
		assertEquals(user1.getDrawings().size(), ((DrawCountUserListResponse) rawDrawCountResponse[1]).getDrawCount());
	}
	
	@Test
	public void testDrawCountFrom(){
		RankingsResource rankingsResource = new RankingsResource();
		
		String pFrom = "20120101-100000";
		
		Response response = rankingsResource.drawCountUserListTimeSpan(pFrom,null,null);
		
		Object[] rawDrawCountResponse =  (Object[]) response.getEntity();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		
		assertEquals(3, rawDrawCountResponse.length);
		assertEquals(user1.getDrawings().size(), ((DrawCountUserListResponse) rawDrawCountResponse[0]).getDrawCount());
	}
	
	@Test
	public void testDrawCountFromTo() {
		RankingsResource rankingsResource = new RankingsResource();
		
		String pFrom = "20120101-100000";
		String pTo = "20120101-130000";
		
		Response response = rankingsResource.drawCountUserListTimeSpan(pFrom,pTo,null);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		Object[] rawDrawCountResponse =  (Object[]) response.getEntity();
		
		assertEquals(2, rawDrawCountResponse.length);
		assertEquals(user1.getDrawings().size(), ((DrawCountUserListResponse) rawDrawCountResponse[0]).getDrawCount());
	}
	

}

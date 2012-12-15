package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

import static org.mockito.Matchers.anyString;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAlcoholLevel extends AbstractMockingTest {
	private Account account1;
	private Box box1;
	private Keg keg1;
	private User user1;

	@Before
	public void setupData() {

		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101,
				Sex.MALE, 85, UserType.USER, account1);
		User user2 = createUser("Bettina", "img/user2", "user2-pw", 202,
				Sex.FEMALE, 85, UserType.USER, account1);

		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		cal.add(Calendar.MINUTE, -1);
		
		createDrawing(2, cal.getTime(), keg1, user1);
		cal.add(Calendar.HOUR, -1);
		createDrawing(2, cal.getTime(), keg1, user1);
		cal.add(Calendar.HOUR, -1);
		createDrawing(2, cal.getTime(), keg1, user1);
		cal.add(Calendar.HOUR, -1);
		createDrawing(2, cal.getTime(), keg1, user1);
		cal.add(Calendar.HOUR, -1);
		createDrawing(2, cal.getTime(), keg1, user2);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(anyString())).thenReturn(account1);
		mockAuthService(authService);
	}

	@Test
	public void testSimple() {
		AlcoholLevelResource alcoholLevelResource = new AlcoholLevelResource();

		Response response = alcoholLevelResource.retrieveAlcoholLevel(null, user1);
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		AlcoholLevelResponse alcoholLevelResponse = (AlcoholLevelResponse) response.getEntity();
		

		assertEquals(2, alcoholLevelResponse.getAlcoholLevel(), 2);
	}
}

package de.kile.zapfmaster2000.rest.api.member;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.members.MembersResource;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestMemberResource extends AbstractMockingTest {

	private Account account1;
	private Box box1;
	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setupData() {
		// truncate at first! This way, we get rid of any achievements that are
		// being created on start up
		truncate();

		account1 = createAccount("foo-account");
		box1 = createBox("box-pp", "somewhere", "1.0", account1);
		user1 = createUser("user1", "img/user1", "user1-pw", 101, Sex.MALE, 85,
				UserType.USER, account1);
		user2 = createUser("user2", "img/user2", "user2-pw", 202, Sex.MALE, 85,
				UserType.USER, account1);
		user3 = createUser("user3", "img/user3", "user3-pw", 303, Sex.MALE, 85,
				UserType.USER, account1);
	}

	@Before
	public void installMocks() {
		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(null)).thenReturn(account1);
		mockAuthService(authService);
	}

	@Test
	public void testSimple() {
		MembersResource membersResource = new MembersResource();
		Response response = membersResource.retrieveMembers(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

}

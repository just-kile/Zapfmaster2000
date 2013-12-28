package de.kile.zapfmaster2000.rest.impl.core.challenge;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.challenge.StatusAwareAsynchronousResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestChallengeService extends AbstractDatabaseTest {

	private Account account1;

	private Account account2;

	private User user1;

	private User user2;

	private User user3;

	@Before
	public void createEntities() {
		account1 = createAccount("a1");
		account2 = createAccount("a2");

		user1 = createUser("u1", "", "", 0, Sex.MALE, 0, UserType.USER,
				account1);
		user2 = createUser("u2", "", "", 0, Sex.MALE, 0, UserType.USER,
				account1);
		user3 = createUser("u3", "", "", 0, Sex.MALE, 0, UserType.USER,
				account2);
	}

	@Test
	public void testRetrieveUsers() {
		ChallengeServiceImpl service = new ChallengeServiceImpl();
		service.rememberUser(user1, mock(StatusAwareAsynchronousResponse.class));
		service.rememberUser(user2, mock(StatusAwareAsynchronousResponse.class));
		service.rememberUser(user3, mock(StatusAwareAsynchronousResponse.class));

		List<User> users = service.retrieveLoggedInUsers(account1);
		assertEquals(2, users.size()); // user3 is used in account 2!

		assertEquals(user1.getId(), users.get(0).getId());
		assertEquals(user2.getId(), users.get(1).getId());
	}

	@Test
	public void testRetrieveUsersFastRemeberings() {
		ChallengeServiceImpl service = new ChallengeServiceImpl();
		service.rememberUser(user1, mock(StatusAwareAsynchronousResponse.class));
		service.rememberUser(user1, mock(StatusAwareAsynchronousResponse.class));
		service.rememberUser(user1, mock(StatusAwareAsynchronousResponse.class));

		List<User> users = service.retrieveLoggedInUsers(account1);

		// only user 1 was remembers. He should be returned once!
		assertEquals(1, users.size());
		assertEquals(user1.getId(), users.get(0).getId());
	}

	@Test
	public void testRetriveUsersGcm() {
		createToken("t1", user1, account1, null, "gcm1");
		createToken("t2", user2, account1, null, null);
		createToken("t3", user3, account2, null, "gcm2");

		ChallengeServiceImpl service = new ChallengeServiceImpl();
		List<User> users = service.retrieveLoggedInUsers(account1);

		// user1 has gcm, user2 not, user3 is from wrong account
		assertEquals(1, users.size());
		assertEquals(user1.getId(), users.get(0).getId());
	}

}

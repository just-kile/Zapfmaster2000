package de.kile.zapfmaster2000.rest.impl.core.statistics;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.UserResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestUserResponseBuilder extends AbstractMockingTest {
	private Account account1;
	private User user1;

	@Before
	public void setupData() {
		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101, Sex.MALE,
				85, UserType.USER, account1);
		createUser("Bettina", "img/user2", "user2-pw", 202,
				Sex.FEMALE, 85, UserType.USER, account1);

	}

	@Test
	public void simpleTest() {

		UserResponse userResponse = UserResponseBuilder.retrieveUserResponse(
				user1.getId(), account1);

		assertConfirms(user1, userResponse);
	}

	public void assertConfirms(User expected, UserResponse actual) {

		assertEquals(expected.getId(), actual.getUserId());
		assertEquals(expected.getImagePath(), actual.getUserImage());
		assertEquals(expected.getName(), actual.getUserName());
	}

}

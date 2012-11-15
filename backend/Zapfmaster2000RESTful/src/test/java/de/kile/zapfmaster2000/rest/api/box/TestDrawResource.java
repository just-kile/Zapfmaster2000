package de.kile.zapfmaster2000.rest.api.box;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.box.BoxManager;
import de.kile.zapfmaster2000.rest.core.box.DrawManager;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestDrawResource extends AbstractMockingTest {

	@Test
	public void testLoginSuccessful() {
		// setup mocks
		User user = Zapfmaster2000Factory.eINSTANCE.createUser();
		user.setName("Horst");
		user.setImagePath("img/njancat.gif");

		DrawManager drawManager = mock(DrawManager.class);
		BoxManager mgr = mock(BoxManager.class);
		when(mgr.getDrawManager("abcde")).thenReturn(drawManager);
		when(drawManager.login(1123581321)).thenReturn(user);

		mockBoxManager(mgr);

		// run the test
		DrawResource drawResource = new DrawResource();
		Response response = drawResource.login("abcde", 1123581321);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		BoxUserLoginResponse entity = (BoxUserLoginResponse) response
				.getEntity();
		assertEquals("Horst", entity.getUserName());
		assertEquals("img/njancat.gif", entity.getImagePath());
	}

	@Test
	public void testLoginFailureBoxDoesNotExist() {
		// setup mocks
		BoxManager mgr = mock(BoxManager.class);
		when(mgr.getDrawManager(anyString())).thenThrow(
				new IllegalArgumentException());
		mockBoxManager(mgr);

		// run the test
		DrawResource drawResource = new DrawResource();
		Response response = drawResource.login("abcde", 1123581321);
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
	}

	@Test
	public void testLoginFailureUserDoesNotExist() {
		// setup mocks
		DrawManager drawManager = mock(DrawManager.class);
		BoxManager mgr = mock(BoxManager.class);
		when(mgr.getDrawManager("abcde")).thenReturn(drawManager);
		when(drawManager.login(1123581321)).thenReturn(null);

		mockBoxManager(mgr);

		// run the test
		DrawResource drawResource = new DrawResource();
		Response response = drawResource.login("abcde", 1123581321);
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());

		verify(drawManager, times(1)).login(1123581321);
	}

}

package de.kile.zapfmaster2000.rest.core.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

public class TestConfigurationService {

	@Test
	public void testGetInt() {
		ConfigurationService mgr = Zapfmaster2000Core.INSTANCE
				.getConfigurationService();
		assertEquals(3000,
				mgr.getInt(ConfigurationConstants.BOX_LOGIN_MIN_DIFF));

		try {
			mgr.getInt("foo");
			fail();
		} catch (NumberFormatException ex) {
			// pass
		}
	}

	public void testGet() {
		ConfigurationService mgr = Zapfmaster2000Core.INSTANCE
				.getConfigurationService();
		assertEquals("3000",
				mgr.get(ConfigurationConstants.BOX_LOGIN_MIN_DIFF));
		assertEquals(null, mgr.get(ConfigurationConstants.BOX_LOGIN_MIN_DIFF));
	}

}

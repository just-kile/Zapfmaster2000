package de.kile.zapfmaster2000.rest.core.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationConstants;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationManager;

public class TestConfigurationManager {

	@Test
	public void testGetInt() {
		ConfigurationManager mgr = Zapfmaster2000Core.INSTANCE
				.getConfigurationManager();
		assertEquals(3000,
				mgr.getInt(ConfigurationConstants.BOX_LONGIN_MIN_DIFF));

		try {
			mgr.getInt("foo");
			fail();
		} catch (NumberFormatException ex) {
			// pass
		}
	}

	public void testGet() {
		ConfigurationManager mgr = Zapfmaster2000Core.INSTANCE
				.getConfigurationManager();
		assertEquals("3000",
				mgr.get(ConfigurationConstants.BOX_LONGIN_MIN_DIFF));
		assertEquals(null, mgr.get(ConfigurationConstants.BOX_LONGIN_MIN_DIFF));
	}

}

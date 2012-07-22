package de.zapfmaster2000.webservice.config;

/**
 * Registration for all config values.
 * 
 * @author thomas
 * 
 */
public class ConfigRegistrator {

	private static ConfigDescriptor[] descriptors = {
			createConfig("BEER_SIZE", "0.5"),
			createConfig("MIN_DRAW_AMOUNT", "0.02"),
			createConfig("MIN_TICKS", "15"),
			createConfig("TICK_REDUCTION", "0"),
			createConfig("TICK_FACTOR", "5500"),
	};

	public static ConfigDescriptor[] getDescriptors() {
		return descriptors;
	}

	private static ConfigDescriptor createConfig(String pName, String pDefault) {
		ConfigDescriptor descr = new ConfigDescriptor();
		descr.setName(pName);
		descr.setDefaultValue(pDefault);
		return descr;
	}

}

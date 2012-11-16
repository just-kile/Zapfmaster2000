package de.kile.zapfmaster2000.rest.impl.core.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;

/**
 * Implementation of the {@link ConfigurationService} for xml file based
 * configuration values.
 * 
 * @author Thomas Kipar
 */
// TODO: Enhance properties handling! File support does not seem to be enough,
// doesn't it?
// Suggestion: Different scopes (default, file, db) + Resource to invoke
// properties reload via REST
public class FileConfiguratioServiceImpl implements ConfigurationService {

	/** zapfmaster2000 config path */
	private static final String PATH = "zapfmaster2000.cfg.xml";

	/** the properties */
	private final Properties properties;

	public FileConfiguratioServiceImpl() {
		properties = loadProperties();
	}

	@Override
	public int getInt(String pKey) {
		return Integer.parseInt(get(pKey));
	}

	@Override
	public double getDouble(String pKey) {
		return Double.parseDouble(get(pKey));
	}

	@Override
	public String get(String pKey) {
		// TODO: proper handling for cast
		return (String) properties.get(pKey);
	}

	/**
	 * Loads the properties from XML.
	 * 
	 * @return
	 * 
	 * @throws IllegalStateException
	 *             if no properties file was found
	 */
	private Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(
				PATH)) {
			properties.loadFromXML(is);
			return properties;
		} catch (IOException e) {
			throw new IllegalStateException(
					"Could not read properties from file " + PATH, e);
		}
	}
}

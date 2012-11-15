package de.kile.zapfmaster2000.rest.core.configuration;

/**
 * Manager to access configuration stuff related to the Zapfmaster2000.
 * 
 * @author Thomas Kipar
 */
public interface ConfigurationManager {

	/**
	 * Returns a configuration value for the given key
	 * 
	 * @param pKey
	 *            key
	 * @return value as int
	 */
	public int getInt(String pKey);
	
	/**
	 * Returns a configuration value for the given key
	 * 
	 * @param pKey
	 *            key
	 * @return value as double
	 */
	public double getDouble(String pKey);

	/**
	 * Returns a configuration value for the given key
	 * 
	 * @param pKey
	 *            key
	 * @param value
	 *            as string
	 */
	public String get(String pKey);

}

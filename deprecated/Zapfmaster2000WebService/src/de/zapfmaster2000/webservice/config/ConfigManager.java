package de.zapfmaster2000.webservice.config;

public interface ConfigManager {

	public String get(String pKey);

	public double getDouble(String pKey);

	public int getInt(String pKey);

}

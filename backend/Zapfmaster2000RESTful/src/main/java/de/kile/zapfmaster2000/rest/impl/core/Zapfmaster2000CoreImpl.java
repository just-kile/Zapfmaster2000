package de.kile.zapfmaster2000.rest.impl.core;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

public class Zapfmaster2000CoreImpl implements Zapfmaster2000Core {
	
	/** shared instance */
	private static Zapfmaster2000CoreImpl instance;
	
	public synchronized static Zapfmaster2000Core init() {
		if (instance != null) {
			instance = new Zapfmaster2000CoreImpl();
		}
		return instance;
	}

}

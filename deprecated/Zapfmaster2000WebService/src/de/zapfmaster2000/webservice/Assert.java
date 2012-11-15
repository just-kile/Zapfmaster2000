package de.zapfmaster2000.webservice;

public class Assert {

	public static void isNotNull(Object pObject) {
		assert (pObject != null);
	}
	
	public static void isNotNull(Object pObject, String pMessage) {
		assert (pObject != null && pMessage != null);
	}
}

package de.zapfmaster2000.webservice.model;

public class LoginDescriptor {

	private int fUserId;
	
	private long fRfidId;

	public int getUserId() {
		return fUserId;
	}

	public void setUserId(int pUserId) {
		fUserId = pUserId;
	}

	public long getRfidId() {
		return fRfidId;
	}

	public void setRfidId(long pRfidId) {
		fRfidId = pRfidId;
	}
	
	
}

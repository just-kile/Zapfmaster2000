package de.zapfmaster2000.webservice.model;

public class FailedLoginStatus {
	
	public enum Type { NOT_EXISTANT, OTHER_USER_IS_DRAWING, OTHER }
	
	private long fRfidId;
	
	private String fReason;
	
	private Type fType;

	public long getRfidId() {
		return fRfidId;
	}

	public void setRfidId(long pRfidId) {
		fRfidId = pRfidId;
	}

	public String getReason() {
		return fReason;
	}

	public void setReason(String pReason) {
		fReason = pReason;
	}

	public Type getType() {
		return fType;
	}

	public void setType(Type pType) {
		fType = pType;
	}
	
	

}

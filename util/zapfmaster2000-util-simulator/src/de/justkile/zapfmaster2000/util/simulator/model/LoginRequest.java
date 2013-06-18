package de.justkile.zapfmaster2000.util.simulator.model;

public class LoginRequest extends AbstractRequest {

	private long rfidTag;
	
	private String boxPassphrase;

	public long getRfidTag() {
		return rfidTag;
	}

	public void setRfidTag(long rfidTag) {
		this.rfidTag = rfidTag;
	}

	public String getBoxPassphrase() {
		return boxPassphrase;
	}

	public void setBoxPassphrase(String boxPassphrase) {
		this.boxPassphrase = boxPassphrase;
	}
	
	
}

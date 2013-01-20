package de.kile.zapfmaster2000.rest.api.box;

public class LoginRequest extends AbstractBoxRequest {

	private long rfidTag;

	public long getRfidTag() {
		return rfidTag;
	}

	public void setRfidTag(long rfidTag) {
		this.rfidTag = rfidTag;
	}

}

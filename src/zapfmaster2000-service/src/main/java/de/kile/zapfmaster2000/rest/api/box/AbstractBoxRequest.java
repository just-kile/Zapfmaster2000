package de.kile.zapfmaster2000.rest.api.box;

public abstract class AbstractBoxRequest {

	private String boxPassphrase;

	public String getBoxPassphrase() {
		return boxPassphrase;
	}

	public void setBoxPassphrase(String boxPassphrase) {
		this.boxPassphrase = boxPassphrase;
	}

}

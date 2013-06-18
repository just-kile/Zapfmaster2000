package de.justkile.zapfmaster2000.util.simulator.model;

public class TickRequest extends AbstractRequest {

	private int numTicks;

	private String boxPassphrase;
	
	public int getNumTicks() {
		return numTicks;
	}

	public void setNumTicks(int numTicks) {
		this.numTicks = numTicks;
	}

	public String getBoxPassphrase() {
		return boxPassphrase;
	}

	public void setBoxPassphrase(String boxPassphrase) {
		this.boxPassphrase = boxPassphrase;
	}

}

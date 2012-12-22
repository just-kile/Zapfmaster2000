package de.kile.zapfmaster2000.rest.api.push;

public class DrawDraftKitResponse extends LoginDraftKitResponse {

	private double amount;

	public DrawDraftKitResponse() {
		super();
		setType(Type.DRAW);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

package de.kile.zapfmaster2000.rest.api.calibration;

public class CalibrationResponse {
	
	private long boxId;
	
	private double regression;
	
	private double disturbance;
	
	private int tickReduction;

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

	public double getRegression() {
		return regression;
	}

	public void setRegression(double regression) {
		this.regression = regression;
	}

	public double getDisturbance() {
		return disturbance;
	}

	public void setDisturbance(double disturbance) {
		this.disturbance = disturbance;
	}

	public int getTickReduction() {
		return tickReduction;
	}

	public void setTickReduction(int tickReduction) {
		this.tickReduction = tickReduction;
	}
	
}

package de.kile.zapfmaster2000.rest.api.calibration;

public class CalibrationResponse {

	private long boxId;

	private double a0;

	private double a1;

	private double a2;

	public double getA0() {
		return a0;
	}

	public void setA0(double a0) {
		this.a0 = a0;
	}

	public double getA1() {
		return a1;
	}

	public void setA1(double a1) {
		this.a1 = a1;
	}

	public double getA2() {
		return a2;
	}

	public void setA2(double a2) {
		this.a2 = a2;
	}

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

}

package de.kile.zapfmaster2000.rest.api.drawing;

import java.util.ArrayList;
import java.util.List;

public class CalibrationResponse {

	private double a0;

	private double a1;

	private double a2;

	private final List<CalibratedData> data = new ArrayList<>();

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

	public List<CalibratedData> getData() {
		return data;
	}

	public static class CalibratedData {

		private double ticks;

		private double amount;

		public double getTicks() {
			return ticks;
		}

		public void setTicks(double ticks) {
			this.ticks = ticks;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

	}

}

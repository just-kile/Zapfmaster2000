package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.ArrayList;
import java.util.List;

public class DistributionResponse {

	private double expectation;

	private double variance;

	private double degression;

	private final List<CurveValue> normalCurve = new ArrayList<>();
	
	private final List<CurveValue> actualCurve = new ArrayList<>();

	public DistributionResponse(double expectation, double variance,
			double degression) {
		this.expectation = expectation;
		this.variance = variance;
		this.degression = degression;
	}

	public double getExpectation() {
		return expectation;
	}

	public void setExpectation(double expectation) {
		this.expectation = expectation;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getDegression() {
		return degression;
	}

	public void setDegression(double degression) {
		this.degression = degression;
	}

	public List<CurveValue> getNormalCurveValues() {
		return normalCurve;
	}

	public static class CurveValue {

		private double amount;

		private double userCount;

		public CurveValue(double amount, double userCount) {
			super();
			this.amount = amount;
			this.userCount = userCount;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public double getUserCount() {
			return userCount;
		}

		public void setUserCount(double userCount) {
			this.userCount = userCount;
		}

	}
}

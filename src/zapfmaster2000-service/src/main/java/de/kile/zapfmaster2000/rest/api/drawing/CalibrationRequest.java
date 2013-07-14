package de.kile.zapfmaster2000.rest.api.drawing;

import java.util.ArrayList;
import java.util.List;

public class CalibrationRequest {

	private String token;

	private CalibrationValues[] data;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CalibrationValues[] getData() {
		return data;
	}

	public static class CalibrationValues {

		private long drawingId;

		private double measuredAmount;

		public long getDrawingId() {
			return drawingId;
		}

		public void setDrawingId(long drawingId) {
			this.drawingId = drawingId;
		}

		public double getMeasuredAmount() {
			return measuredAmount;
		}

		public void setMeasuredAmount(double measuredAmount) {
			this.measuredAmount = measuredAmount;
		}

	}

}

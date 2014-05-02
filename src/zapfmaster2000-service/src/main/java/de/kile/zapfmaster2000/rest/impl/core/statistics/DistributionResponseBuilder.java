package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.sql.Date;

import de.kile.zapfmaster2000.rest.api.statistics.DistributionResponse;
import de.kile.zapfmaster2000.rest.api.statistics.DistributionResponse.CurveValue;
import de.kile.zapfmaster2000.rest.api.statistics.UserAmountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class DistributionResponseBuilder {

	private final RankingsBuilder rankingsBuilder;

	public DistributionResponseBuilder(RankingsBuilder rankingsBuilder) {
		this.rankingsBuilder = rankingsBuilder;
	}

	public DistributionResponse retrieveAmountResponse(Account account) {

		UserAmountResponse[] amountRankings = retrieveAmountRanking(account);

		double expectation = calcExpectation(amountRankings);
		double variance = calcVariance(amountRankings, expectation);
		double degression = calcDegression(variance);

		DistributionResponse response = new DistributionResponse(expectation,
				variance, degression);
		addNormalCurveValues(response);

		return response;
	}

	private void addNormalCurveValues(DistributionResponse response) {
		int range = 3;
		for (int i = -range; i <= range; i++) {
			double x = response.getExpectation() + i * response.getDegression();
			double p_x = calcNormalValue(response, x);
			response.getNormalCurveValues().add(new CurveValue(x, p_x));
		}
	}

	private double calcNormalValue(DistributionResponse response, double x) {
		double degression = response.getDegression();
		double expectation = response.getExpectation();

		double factor = 1.0 / (degression * Math.sqrt(2 * Math.PI));
		double exponent = -0.5 * square((x - expectation) / degression);
		double powerE = Math.pow(Math.E, exponent);
		double result = factor * powerE;

		return result;
	}

	private double calcDegression(double variance) {
		return Math.sqrt(variance);
	}

	private double calcVariance(UserAmountResponse[] amountRankings,
			double expectation) {
		double variance = 0;
		for (UserAmountResponse amountResponse : amountRankings) {
			double varianceTerm = amountResponse.getAmount() - expectation;
			variance += square(varianceTerm) / amountRankings.length;
		}
		return variance;
	}

	private double calcExpectation(UserAmountResponse[] amountRankings) {
		double expectation = 0;
		for (UserAmountResponse amountResponse : amountRankings) {
			expectation += amountResponse.getAmount();
		}
		expectation /= amountRankings.length;
		return expectation;
	}

	private UserAmountResponse[] retrieveAmountRanking(Account account) {
		Date fromDate = null;
		Date toDate = null;
		int allUsers = -1;
		UserAmountResponse[] amountRankings = rankingsBuilder
				.retrieveUserAmountResponse(fromDate, toDate, allUsers, account);
		return amountRankings;
	}

	private double square(double value) {
		return value * value;
	}

}

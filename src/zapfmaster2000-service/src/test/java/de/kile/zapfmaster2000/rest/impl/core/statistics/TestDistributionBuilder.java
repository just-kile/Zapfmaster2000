package de.kile.zapfmaster2000.rest.impl.core.statistics;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.api.statistics.DistributionResponse;
import de.kile.zapfmaster2000.rest.api.statistics.DistributionResponse.CurveValue;
import de.kile.zapfmaster2000.rest.api.statistics.UserAmountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class TestDistributionBuilder {

	@Test
	public void testNormalCurveValues() {
		RankingsBuilder rankingsBuilder = createRankingsBuilder();
		DistributionResponseBuilder distributionResponseBuilder = new DistributionResponseBuilder(
				rankingsBuilder);
		Account account = null;

		DistributionResponse result = distributionResponseBuilder
				.retrieveAmountResponse(account);

		assertEquals(6, result.getNormalCurveValues().size());
		assertCurveValueEquals(1.0, 0.017727393647752034, result
				.getNormalCurveValues().get(0));
		assertCurveValueEquals(1.25, 0.21596386605275225, result
				.getNormalCurveValues().get(1));
		assertCurveValueEquals(1.5, 0.9678828980765735, result
				.getNormalCurveValues().get(2));
		assertCurveValueEquals(1.75, 1.5957691216057308, result
				.getNormalCurveValues().get(3));
		assertCurveValueEquals(2.0, 0.9678828980765735, result
				.getNormalCurveValues().get(4));
		assertCurveValueEquals(2.25, 0.21596386605275225, result
				.getNormalCurveValues().get(5));
		assertCurveValueEquals(2.5, 0.017727393647752034, result
				.getNormalCurveValues().get(6));
	}

	private void assertCurveValueEquals(double expectedAmount,
			double exectedUserCount, CurveValue value) {
		double epsilon = 0.01;
		assertEquals(expectedAmount, value.getAmount(), epsilon);
		assertEquals(exectedUserCount, value.getUserCount(), epsilon);
	}

	private RankingsBuilder createRankingsBuilder() {
		RankingsBuilder rankingsBuilder = mock(RankingsBuilder.class);
		when(
				rankingsBuilder.retrieveUserAmountResponse(any(Date.class),
						any(Date.class), eq(-1), any(Account.class)))
				.thenReturn(createUserAmountResponse());
		return rankingsBuilder;
	}

	private UserAmountResponse[] createUserAmountResponse() {
		String userName = null;
		String userImage = null;
		int userId = 0;
		return new UserAmountResponse[] {
				new UserAmountResponse(userName, userId, userImage, 1.5),
				new UserAmountResponse(userName, userId, userImage, 2) };
	}
}

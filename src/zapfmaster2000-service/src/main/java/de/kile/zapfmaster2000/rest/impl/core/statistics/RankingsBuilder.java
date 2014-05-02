package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Date;

import de.kile.zapfmaster2000.rest.api.statistics.AchievementUserListResponse;
import de.kile.zapfmaster2000.rest.api.statistics.DrawCountUserListResponse;
import de.kile.zapfmaster2000.rest.api.statistics.UserAmountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public interface RankingsBuilder {

	// TODO add empty user
	/**
	 * Returns an array of {@link UserAmountResponse} over a time period. Might
	 * be empty.
	 * 
	 * @param dFrom
	 *            can be <code>null</code> for all available drawings
	 * @param dTo
	 *            can be <code>null</code> for until now
	 * @param maxResults
	 *            maximal number of results. -1 for all results.
	 * @param account
	 * @return
	 */
	public abstract UserAmountResponse[] retrieveUserAmountResponse(Date dFrom,
			Date dTo, int maxResults, Account account);

	/**
	 * Returns an array of {@link DrawCountUserListResponse} over a time period.
	 * Might be empty.
	 * 
	 * @param dFrom
	 *            optional (default: full list)
	 * @param dTo
	 *            optional (default: until now)
	 * @param maxResults
	 *            maximal number of results. -1 for all results.
	 * @param account
	 * @return
	 */
	public abstract DrawCountUserListResponse[] retrieveDrawCountUserListResponse(
			Date dFrom, Date dTo, int maxResults, Account account);

	/**
	 * Returns an array of {@link AchievementUserListResponse} over a time
	 * period. Might be empty.
	 * 
	 * @param dFrom
	 *            optional (default: full list)
	 * @param dTo
	 *            optional (default: until now)
	 * @param maxResults
	 *            maximal number of results. -1 for all results.
	 * @param account
	 * @return
	 */
	public abstract AchievementUserListResponse[] retrieveAchievementUserListResponse(
			Date dFrom, Date dTo, int maxResults, Account account);

}
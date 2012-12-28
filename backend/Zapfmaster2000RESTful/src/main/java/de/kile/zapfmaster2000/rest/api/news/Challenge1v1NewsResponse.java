package de.kile.zapfmaster2000.rest.api.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Challenge1v1NewsResponse extends AbstractNewsResponse {

	private int challengeDuration;

	private String startDate;

	private long challengeId;

	private final List<ChallengeParticipant> team1 = new ArrayList<>();

	private final List<ChallengeParticipant> team2 = new ArrayList<>();

	public Challenge1v1NewsResponse() {
		super(Type.CHALLENGE_STARTED);
	}

	public int getChallengeDuration() {
		return challengeDuration;
	}

	public void setChallengeDuration(int challengeDuration) {
		this.challengeDuration = challengeDuration;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void loadStartDate(Date pDate) {
		this.startDate = doLoadDate(pDate);
	}

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public List<ChallengeParticipant> getTeam1() {
		return team1;
	}

	public List<ChallengeParticipant> getTeam2() {
		return team2;
	}

	public static class ChallengeParticipant {

		private long userId;

		private String userName;

		private String userImage;

		private double amount;

		private boolean won;

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserImage() {
			return userImage;
		}

		public void setUserImage(String userImage) {
			this.userImage = userImage;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public boolean isWon() {
			return won;
		}

		public void setWon(boolean won) {
			this.won = won;
		}

	}

}

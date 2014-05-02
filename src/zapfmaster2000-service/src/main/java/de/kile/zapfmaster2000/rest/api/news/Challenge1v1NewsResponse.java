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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + challengeDuration;
		result = prime * result + (int) (challengeId ^ (challengeId >>> 32));
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((team1 == null) ? 0 : team1.hashCode());
		result = prime * result + ((team2 == null) ? 0 : team2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge1v1NewsResponse other = (Challenge1v1NewsResponse) obj;
		if (challengeDuration != other.challengeDuration)
			return false;
		if (challengeId != other.challengeId)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (team1 == null) {
			if (other.team1 != null)
				return false;
		} else if (!team1.equals(other.team1))
			return false;
		if (team2 == null) {
			if (other.team2 != null)
				return false;
		} else if (!team2.equals(other.team2))
			return false;
		return true;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(amount);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + (int) (userId ^ (userId >>> 32));
			result = prime * result
					+ ((userImage == null) ? 0 : userImage.hashCode());
			result = prime * result
					+ ((userName == null) ? 0 : userName.hashCode());
			result = prime * result + (won ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChallengeParticipant other = (ChallengeParticipant) obj;
			if (Double.doubleToLongBits(amount) != Double
					.doubleToLongBits(other.amount))
				return false;
			if (userId != other.userId)
				return false;
			if (userImage == null) {
				if (other.userImage != null)
					return false;
			} else if (!userImage.equals(other.userImage))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (won != other.won)
				return false;
			return true;
		}

	}

}

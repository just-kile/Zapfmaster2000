package de.zapfmaster2000.webservice.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CHALLENGE_PARTICIPANTS")
public class ChallengeParticipant {

	private int challengeId;
	
	private int team;
	
	private int userId;

	@Id
	@Column(name = "CHALLENGE_ID")
	public int getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	@Column(name = "TEAM")
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	

}

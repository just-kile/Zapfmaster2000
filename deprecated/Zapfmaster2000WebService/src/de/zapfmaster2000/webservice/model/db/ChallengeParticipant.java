package de.zapfmaster2000.webservice.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CHALLENGE_PARTICIPANTS")
public class ChallengeParticipant {

	private int challengePartipicantId;
	
	private int challengeId;
	
	private int team;
	
	private int userId;
	
	private boolean won;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHALLENGE_PARTIPICANT_ID")
	public int getChallengePartipicantId() {
		return challengePartipicantId;
	}

	public void setChallengePartipicantId(int challengePartipicantId) {
		this.challengePartipicantId = challengePartipicantId;
	}

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
	
	@Column(name = "WON")
	public boolean getWon() {
		return won;
	}
	
	public void setWon(boolean won) {
		this.won = won;
	}

}

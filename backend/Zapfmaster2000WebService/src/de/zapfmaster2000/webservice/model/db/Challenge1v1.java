package de.zapfmaster2000.webservice.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CHALLENGE_1V1")
public class Challenge1v1 {
	
	private int challenge1v1Id;
	
	private int challengeId;
	
	private int duration;
	
	private Date startTime;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHALLENGE_1V1_ID")
	public int getChallenge1v1Id() {
		return challenge1v1Id;
	}

	public void setChallenge1v1Id(int challenge1v1Id) {
		this.challenge1v1Id = challenge1v1Id;
	}

	@Column(name = "CHALLENGE_ID")
	public int getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	@Column(name = "DURATION")
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	

}

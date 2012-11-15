package de.zapfmaster2000.webservice.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CHALLENGES")
public class Challenge {

	private int challengeId;
	
	private String type;

	@Id
	@Column(name = "CHALLENGE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	@Column(name = "CHALLENGE_TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

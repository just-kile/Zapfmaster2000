package de.beeromat.core.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table entry representing an gained achievement (T_GAINED_ACHIEVEMENTS).
 * 
 * @author thomas
 */
@Entity
@Table(name = "T_GAINED_ACHIEVEMENTS")
public class GainedAchievement {

	private int fGainedAchievementId;

	private int fAchievementId;

	private int fUserId;

	private int fGroupId;
	
	private Date fDate;

	@Id
	@Column(name = "GAINED_ACHIEVEMENT_ID")
	public int getGainedAchievementId() {
		return fGainedAchievementId;
	}

	public void setGainedAchievementId(int pGainedAchievementId) {
		fGainedAchievementId = pGainedAchievementId;
	}

	@Column(name = "ACHIEVEMENT_ID")
	public int getAchievementId() {
		return fAchievementId;
	}

	public void setAchievementId(int pAchievementId) {
		fAchievementId = pAchievementId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return fUserId;
	}

	public void setUserId(int pUserId) {
		fUserId = pUserId;
	}

	@Column(name = "GROUP_ID")
	public int getGroupId() {
		return fGroupId;
	}

	public void setGroupId(int pGroupId) {
		fGroupId = pGroupId;
	}
	
	@Column(name = "DATE")
	public Date getDate() {
		return fDate;
	}

	public void setDate(Date pDate) {
		fDate = pDate;
	}
	
	

}

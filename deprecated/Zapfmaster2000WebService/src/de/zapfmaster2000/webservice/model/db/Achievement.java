package de.zapfmaster2000.webservice.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pojo representing an entry of the table T_ACHIEVEMENTS.
 * 
 * @author thomas
 */

@Entity
@Table(name = "T_ACHIEVEMENTS")
public class Achievement {

	/** achievment id */
	private int fAchievmentId;

	/** name of the achievment */
	private String fName;

	/** description of the achivement */
	private String Description;

	/** image path */
	private String fImagePath;

	/** public flag */
	private boolean fPublic;

	/** the type of the achivemenht */
	private String fType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACHIEVEMENT_ID")
	public int getAchievmentId() {
		return fAchievmentId;
	}

	public void setAchievmentId(int pAchievmentId) {
		fAchievmentId = pAchievmentId;
	}

	@Column(name = "NAME")
	public String getName() {
		return fName;
	}

	public void setName(String pName) {
		fName = pName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return Description;
	}

	public void setDescription(String pDescription) {
		Description = pDescription;
	}

	@Column(name = "IMAGE_PATH")
	public String getImagePath() {
		return fImagePath;
	}

	public void setImagePath(String pImagePath) {
		fImagePath = pImagePath;
	}

	@Column(name = "PUBLIC")
	public boolean isPublic() {
		return fPublic;
	}

	public void setPublic(boolean pPublic) {
		fPublic = pPublic;
	}

	@Column(name = "TYPE")
	public String getType() {
		return fType;
	}

	public void setType(String pType) {
		fType = pType;
	}

}

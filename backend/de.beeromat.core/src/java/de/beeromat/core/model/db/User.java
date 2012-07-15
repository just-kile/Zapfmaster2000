package de.beeromat.core.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table entry: T_USERS
 * 
 * @author thomas
 */
@Entity
@Table(name = "T_USERS")
public class User {

	/** id of the user */
	private int fUserId;
	
	/** the name of the user */
	private String fName;
	
	/** password of the user */
	private String fPassword;
	
	/** image path */
	private String fImagePath;
	
	/** the rfid tag */
	private long fRfidTag;

    @Id
    @Column(name="USER_ID")
	public int getUserId() {
		return fUserId;
	}

	public void setUserId(int pUserId) {
		fUserId = pUserId;
	}

    @Column(name="NAME")
	public String getName() {
		return fName;
	}

	public void setName(String pName) {
		fName = pName;
	}

    @Column(name="PASSWORD")
	public String getPassword() {
		return fPassword;
	}

	public void setPassword(String pPassword) {
		fPassword = pPassword;
	}

    @Column(name="IMAGE_PATH")
	public String getImagePath() {
		return fImagePath;
	}

	public void setImagePath(String pImagePath) {
		fImagePath = pImagePath;
	}

    @Column(name="RFID_TAG")
	public long getRfidTag() {
		return fRfidTag;
	}

	public void setRfidTag(long pRfidTag) {
		fRfidTag = pRfidTag;
	}

	
	
	
	
}

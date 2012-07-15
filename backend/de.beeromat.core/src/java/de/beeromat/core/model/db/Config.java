package de.beeromat.core.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Poje representing a single entry of the T_CONFIG table.
 * 
 * @author thomas
 *
 */
@Entity
@Table(name = "T_CONFIG")
public class Config {
	
	/** the key */
	private String fKey;
	
	/** the value */
	private String fValue;

	@Id
	@Column(name = "CONF_KEY")
	public String getKey() {
		return fKey;
	}

	public void setKey(String pKey) {
		fKey = pKey;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return fValue;
	}

	public void setValue(String pValue) {
		fValue = pValue;
	}
	
	

}

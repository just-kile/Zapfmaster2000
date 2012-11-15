package de.zapfmaster2000.webservice.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table entity: T_NEWS
 * 
 * @author thomas
 *
 */
@Entity
@Table(name = "T_NEWS")
public class News {

	
	private int fNewsId;
	
	private String fNewsType;
	
	private String fContents;
	
	private String fImagePath;

	@Id
	@Column(name = "NEWS_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getNewsId() {
		return fNewsId;
	}

	public void setNewsId(int pNewsId) {
		fNewsId = pNewsId;
	}

	@Column(name = "TYPE")
	public String getNewsType() {
		return fNewsType;
	}

	public void setNewsType(String pNewsType) {
		fNewsType = pNewsType;
	}
	
	@Column(name = "CONTENTS")
	public String getContents() {
		return fContents;
	}

	public void setContents(String pContents) {
		fContents = pContents;
	}

	@Column(name = "IMAGE_PATH")
	public String getImagePath() {
		return fImagePath;
	}

	public void setImagePath(String pImagePath) {
		fImagePath = pImagePath;
	}
	


		
}

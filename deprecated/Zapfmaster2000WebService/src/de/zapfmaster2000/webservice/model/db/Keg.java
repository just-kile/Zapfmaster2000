package de.zapfmaster2000.webservice.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table entry of T_KEGS.
 * 
 * @author thomas
 * 
 */

@Entity
@Table(name = "T_KEGS")
public class Keg {

	/** the id of the keg */
	private int fKegId;

	/** the brand of the keg */
	private String fBrand;

	/** the size of the keqg */
	private double fSize;

	/** start time of the keg */
	private Date fStart;

	/** end date of the keg */
	private Date fEnd;

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="KEG_ID")
	public int getKegId() {
		return fKegId;
	}

	public void setKegId(int pKegId) {
		fKegId = pKegId;
	}

    @Column(name="BRAND")
	public String getBrand() {
		return fBrand;
	}

	public void setBrand(String pBrand) {
		fBrand = pBrand;
	}

    @Column(name="SIZE")
	public double getSize() {
		return fSize;
	}

	public void setSize(double pSize) {
		fSize = pSize;
	}

    @Column(name="START_DATE")
	public Date getStart() {
		return fStart;
	}

	public void setStart(Date pStart) {
		fStart = pStart;
	}
	
    @Column(name="END_DATE")
	public Date getEnd() {
		return fEnd;
	}

	public void setEnd(Date pEnd) {
		fEnd = pEnd;
	}

}

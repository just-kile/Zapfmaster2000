package de.zapfmaster2000.webservice.model.db;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Table entry of T_DRAWINGS.
 * 
 * @author thomas
 */
@Entity
@Table(name = "T_DRAWINGS")
public class Drawing {

	private int fDrawingId;
	
	private int fUserId;
	
	private int fRawAmount;
	
	private double fRealAmount;
	
	private Date fDate;
	
	private double fDuration;
	
	private int fKegId;

	@Id
	@Column(name = "DRAWING_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDrawingId() {
		return fDrawingId;
	}

	public void setDrawingId(int pDrawingId) {
		fDrawingId = pDrawingId;
	}

	@Column(name = "USER_ID")
	public int getUserId() {
		return fUserId;
	}

	public void setUserId(int pUserId) {
		fUserId = pUserId;
	}

	@Column(name = "RAW_AMOUNT")
	public int getRawAmount() {
		return fRawAmount;
	}

	public void setRawAmount(int pRawAmount) {
		fRawAmount = pRawAmount;
	}

	@Column(name = "REAL_AMOUNT")
	public double getRealAmount() {
		return fRealAmount;
	}

	public void setRealAmount(double pRealAmout) {
		fRealAmount = pRealAmout;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return fDate;
	}

	public void setDate(Date pDate) {
		fDate = pDate;
	}

	@Column(name = "DURATION")
	public double getDuration() {
		return fDuration;
	}

	public void setDuration(double pDuration) {
		fDuration = pDuration;
	}

	@Column(name = "KEG_ID")
	public int getKegId() {
		return fKegId;
	}

	public void setKegId(int pKegId) {
		fKegId = pKegId;
	}
	
	
	
	
}

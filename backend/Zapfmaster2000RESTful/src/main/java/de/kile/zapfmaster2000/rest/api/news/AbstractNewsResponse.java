package de.kile.zapfmaster2000.rest.api.news;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;

public class AbstractNewsResponse {

	private static final Logger LOG = Logger.getLogger(AbstractNewsResponse.class);
	
	private Type type;

	private String image;

	private String date;

	public AbstractNewsResponse(Type pType) {
		type = pType;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void loadDate(Date pDate) {
		date = doLoadDate(pDate);
	}

	protected String doLoadDate(Date pDate) {
		if (pDate != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);
			return format.format(pDate);
		} else {
			LOG.warn("Could not load date: Date is null.");
			return null;
		}

	}

	public enum Type {
		DRAWING, ACHIEVEMENT, CHALLENGE_STARTED, CHALLENGE_DECLINED, CHALLENGE_DONE
	}

}

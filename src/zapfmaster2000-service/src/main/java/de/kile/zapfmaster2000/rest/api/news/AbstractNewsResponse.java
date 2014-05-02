package de.kile.zapfmaster2000.rest.api.news;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;

public class AbstractNewsResponse {

	private static final Logger LOG = Logger
			.getLogger(AbstractNewsResponse.class);

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNewsResponse other = (AbstractNewsResponse) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public enum Type {
		DRAWING, ACHIEVEMENT, CHALLENGE_STARTED, CHALLENGE_DECLINED, CHALLENGE_DONE, NEW_KEG, NEW_USER
	}

}

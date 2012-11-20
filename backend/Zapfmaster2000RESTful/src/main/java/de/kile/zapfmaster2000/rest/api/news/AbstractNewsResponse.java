package de.kile.zapfmaster2000.rest.api.news;

import java.util.Date;

public class AbstractNewsResponse {

	private Type type;

	private String image;

	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public enum Type {
		DRAWING, ACHIEVEMENT
	}

}

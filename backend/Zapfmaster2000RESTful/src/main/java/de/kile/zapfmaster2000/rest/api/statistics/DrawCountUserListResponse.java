package de.kile.zapfmaster2000.rest.api.statistics;

public class DrawCountUserListResponse {
	
private String name;
	
	private long id;
	
	private String image;
	
	private long drawCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(long drawcount) {
		this.drawCount = drawcount;
	}
	

}

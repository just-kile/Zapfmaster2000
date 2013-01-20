package de.kile.zapfmaster2000.rest.api.news;

public class NewKegNewsReponse extends AbstractNewsResponse {

	private String brand;

	private int size;

	private String location;

	private long kegId;

	private long boxId;

	public NewKegNewsReponse() {
		super(Type.NEW_KEG);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getKegId() {
		return kegId;
	}

	public void setKegId(long kegId) {
		this.kegId = kegId;
	}

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

}

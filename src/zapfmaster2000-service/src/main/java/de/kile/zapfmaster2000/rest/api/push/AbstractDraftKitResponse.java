package de.kile.zapfmaster2000.rest.api.push;


public abstract class AbstractDraftKitResponse {

	private long boxId;

	private Type type;

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		LOGIN, DRAW, LOGOUT
	}

}

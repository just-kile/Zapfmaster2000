package de.kile.zapfmaster2000.rest.api.admin;

import de.kile.zapfmaster2000.rest.api.admin.AdminResponse.Type;

public class AdminInfo {

	private String adminName;
	private Type type;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}

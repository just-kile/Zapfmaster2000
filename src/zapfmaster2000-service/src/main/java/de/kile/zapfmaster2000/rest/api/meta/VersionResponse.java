package de.kile.zapfmaster2000.rest.api.meta;

public class VersionResponse {

	private String buildNumber;

	private String buildTime;

	private String pomVersion;

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public String getPomVersion() {
		return pomVersion;
	}

	public void setPomVersion(String pomVersion) {
		this.pomVersion = pomVersion;
	}

}

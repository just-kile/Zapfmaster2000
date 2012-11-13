package de.kile.zapfmaster2000.rest.api.login;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials {

	private String name;
	private String passphrase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

}

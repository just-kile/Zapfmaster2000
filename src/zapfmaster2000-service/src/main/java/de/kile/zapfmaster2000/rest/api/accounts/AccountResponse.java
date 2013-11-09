package de.kile.zapfmaster2000.rest.api.accounts;

public class AccountResponse {

	private long accountId;

	private String name;
	

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

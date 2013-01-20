package de.kile.zapfmaster2000.connector;

public interface ConnectorListener {
	
	public void onLogout();
	
	public void onLogin(String pUserName, String pImagePath);
	
	public void onLoginFailure();
	
	public void onDraw(double amount);

}

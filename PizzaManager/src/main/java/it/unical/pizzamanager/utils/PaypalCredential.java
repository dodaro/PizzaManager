package it.unical.pizzamanager.utils;

public class PaypalCredential {

	
	private String clientId;
	
	private String secret;
	
	public PaypalCredential() {
		this.clientId="";
		this.secret="";
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}

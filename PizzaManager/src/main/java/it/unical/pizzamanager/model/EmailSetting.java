package it.unical.pizzamanager.model;

public class EmailSetting {

	private String email;
	
	private String password;
	
	public EmailSetting() {
		this.email="";
		this.password="";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

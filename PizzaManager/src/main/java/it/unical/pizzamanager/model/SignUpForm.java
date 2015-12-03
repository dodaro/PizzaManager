package it.unical.pizzamanager.model;

public class SignUpForm {

	private String email;
	private String password;
	
	public SignUpForm() {
		this.email = "";
		this.password = "";
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

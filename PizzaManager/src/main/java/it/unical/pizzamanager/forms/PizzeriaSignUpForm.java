package it.unical.pizzamanager.forms;

public class PizzeriaSignUpForm {

	private String email;
	private String password;

	private String name;
	private Double latitude;
	private Double longitude;

	public PizzeriaSignUpForm() {
		this.email = "";
		this.password = "";
		this.name = "";
		this.latitude = 0.0;
		this.longitude = 0.0;
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

	public String getName() {
		return name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
}

package it.unical.pizzamanager.forms;

public class PizzeriaSignUpForm {

	private String email;
	private String password;

	private String name;
	private String phoneNumber;

	private String street;
	private Integer number;
	private String city;

	private Double latitude;
	private Double longitude;

	public PizzeriaSignUpForm() {
		this.email = "";
		this.password = "";
		this.name = "";
		this.phoneNumber = "";
		this.street = "";
		this.number = 0;
		this.city = "";
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

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PizzeriaSignUpForm [email=" + email + ", password=" + password + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", street=" + street + ", number=" + number
				+ ", city=" + city + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}

package it.unical.pizzamanager.forms;

public class UserSignUpForm {

	private String email;
	private String password;

	private String username;
	private String firstName;
	private String lastName;
	
	private String street;
	private Integer number;
	private String city;

	private Double latitude;
	private Double longitude;

	public UserSignUpForm() {
		this.email = "";
		this.password = "";
		this.username = "";
		this.firstName = "";
		this.lastName = "";
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}

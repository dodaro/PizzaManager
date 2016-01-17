package it.unical.pizzamanager.forms;

public class UserSignUpForm {

	private String email;
	private String password;

	private String username;
	private String firstName;
	private String lastName;

	public UserSignUpForm() {
		this.email = "";
		this.password = "";
		this.username = "";
		this.firstName = "";
		this.lastName = "";
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
}

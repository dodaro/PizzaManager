package it.unical.pizzamanager.model;

import it.unical.pizzamanager.persistence.dto.User;

public class UserDisplay {

	private String email;

	private String password;

	private int id;

	public UserDisplay() {
		id = 0;
		this.email = "";
		this.password = "";
	}

	public UserDisplay(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

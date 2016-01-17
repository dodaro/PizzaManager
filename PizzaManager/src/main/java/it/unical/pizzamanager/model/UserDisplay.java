package it.unical.pizzamanager.model;

public class UserDisplay {

	private String userName;

	private int id;
	

	public UserDisplay() {
		this.userName="";
		id=0;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
}

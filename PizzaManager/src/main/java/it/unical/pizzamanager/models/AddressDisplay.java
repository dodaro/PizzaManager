package it.unical.pizzamanager.models;

public class AddressDisplay {

	
	String city;
	
	String street;
	
	int number;
	
	public AddressDisplay() {
		this.city="";
		this.street="";
		this.number=0;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}

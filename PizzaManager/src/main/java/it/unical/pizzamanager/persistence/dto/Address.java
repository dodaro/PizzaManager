package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private static final int NO_NUMBER = -1;

	private String street;
	private int number;
	private String city;

	public Address() {
		this.street = "";
		this.number = NO_NUMBER;
		this.city = "";
	}

	public Address(String street, int number, String city) {
		this.street = street;
		this.number = number;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
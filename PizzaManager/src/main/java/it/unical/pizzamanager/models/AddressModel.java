package it.unical.pizzamanager.models;

public class AddressModel {

	private String City;
	private String Street;
	private String Number;
	private Integer id;
	
	public AddressModel() {
		// TODO Auto-generated constructor stub
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}

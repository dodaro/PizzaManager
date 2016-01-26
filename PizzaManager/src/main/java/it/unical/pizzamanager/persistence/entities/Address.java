package it.unical.pizzamanager.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "addresses")
public class Address {

	private static final int NO_NUMBER = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "street")
	private String street;

	@Column(name = "number")
	private Integer number;

	@Column(name = "city")
	private String city;

	@OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
	private Account account;

	public Address() {
		this.street = "";
		this.number = NO_NUMBER;
		this.city = "";
		this.account = null;
	}

	public Address(String street, Integer number, String city) {
		this.id = DatabaseHandler.NO_ID;
		this.street = street;
		this.number = number;
		this.city = city;
		this.account = null;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
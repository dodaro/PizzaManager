package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Customers are people who bought something from some pizzeria but don't have an account on the
 * system.
 */
@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customersGenerator", sequenceName = "customers_sequence", initialValue = 1)
public abstract class Customer implements Serializable {

	private static final long serialVersionUID = 1689591845450158384L;

	private static final int NO_ID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customersGenerator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ElementCollection
	@CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "customer") )
	@Column(name = "address")
	private Address address;

	@Column(name = "phone_number")
	private String phoneNumber;

	public Customer() {
		this.id = NO_ID;
		this.firstName = "";
		this.lastName = "";
		this.address = new Address();
		this.phoneNumber = "";
	}

	public Customer(String firstName, String lastName, Address address, String phoneNumber) {
		this.id = NO_ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

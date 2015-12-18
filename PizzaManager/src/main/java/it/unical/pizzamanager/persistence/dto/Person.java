package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Persons are people who bought something from some pizzeria but may or may not have an account on
 * the system (persons with an account are Users).
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1812329632392004600L;

	private static final int NO_ID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	/*
	 * L'annotazione @Cascade serve in modo che, se una persona ha un Address impostato, non bisogna
	 * salvare appositamente prima l'address prima di salvare la persona.
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "address")
	private Address address;

	@Column(name = "phone_number")
	private String phoneNumber;

	public Person(String firstName, String lastName, Address address, String phoneNumber) {
		this.id = NO_ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Person(String firstName, String lastName, String phoneNumber) {
		this.id = NO_ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = null;
		this.phoneNumber = phoneNumber;
	}

	public Person() {
		id = NO_ID;
		firstName = "";
		lastName = "";
		address = null;
		phoneNumber = "";
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

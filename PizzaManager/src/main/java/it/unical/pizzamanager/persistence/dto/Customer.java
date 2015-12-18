package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Persons who bought something from some pizzeria but may or may not have an account on the system
 * (persons with an account are Users).
 */
@Entity
@Table(name = "customers")
public class Customer extends Person {

	private static final long serialVersionUID = 1355500622048488809L;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName, phoneNumber);
	}
}

package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Persons are people who bought something from some pizzeria but may or may not have an account on
 * the system (persons with an account are Users).
 */
@Entity
@Table(name = "customers")
public class Customer extends Person {

	private static final long serialVersionUID = 6675080800785762534L;
	
	@Column(name = "foo")
	private Integer foo;

	public Customer() {
		super();
		this.foo = 0;
	}

	public Customer(String firstName, String lastName, String phoneNumber, Integer foo) {
		super(firstName, lastName, phoneNumber);
		this.foo = foo;
	}
}

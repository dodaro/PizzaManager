package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pizzerias")
public class Pizzeria {

	private int id;

	private String name;

	private String email;

	private String description;

	private Address address;

	private String phoneNumber;
}

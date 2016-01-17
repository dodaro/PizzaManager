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
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "accountsGenerator", sequenceName = "accounts_sequence", initialValue = 1)
public abstract class Account implements Serializable {

	private static final long serialVersionUID = 6208476687075170106L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountsGenerator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", length = 255, unique = true, nullable = false)
	private String email;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@OneToOne
	@JoinColumn(name = "address")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;

	@OneToOne
	@JoinColumn(name = "location")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Location location;

	public Account() {
		this.id = DatabaseHandler.NO_ID;
		this.email = "";
		this.password = "";
		this.address = null;
		this.location = null;
	}

	public Account(String email, String password) {
		this.id = DatabaseHandler.NO_ID;
		this.email = email;
		this.password = password;
		this.address = null;
		this.location = null;
	}

	public Account(String email, String password, Location location) {
		this.id = DatabaseHandler.NO_ID;
		this.email = email;
		this.password = password;
		this.address = null;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
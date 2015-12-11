package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "cart")
@SequenceGenerator(name = "cartsGenerator", sequenceName = "cartsSequence", initialValue = 1)
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993247062854443717L;


	@OneToOne
	@JoinColumn(name = "user")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartsGenerator")
	@Column(name = "id")
	private Integer id;

	public Cart() {
		id = DatabaseHandler.NO_ID;
		user = null;
	}

	public Cart(User user) {
		super();
		this.id = DatabaseHandler.NO_ID;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

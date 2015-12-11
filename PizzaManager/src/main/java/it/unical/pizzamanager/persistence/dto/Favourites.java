package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favourites")
public class Favourites implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7166272043751535111L;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codice")
	private Integer codice;


	public Favourites() {
		codice = 0;
		user=null;
	}

	public Favourites(Integer codice, User user) {
		this.codice = codice;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	@Override
	public String toString() {
		return user.toString() + " - " + codice + " - ";
	}
}

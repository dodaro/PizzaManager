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
@Table(name = "payment")
public class Payment implements Serializable {

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

	@Column(name = "saved", nullable = false)
	private boolean saved;
	
	@Column(name = "payed", nullable = false)
	private boolean payed;

	public Payment() {
		codice = 0;
		saved = false;
		payed = false;
		user=null;
	}

	public Payment(Integer codice, boolean saved, boolean payed, User user) {
		this.codice = codice;
		this.saved = saved;
		this.payed = payed;
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

	public boolean getSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	public boolean getPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	@Override
	public String toString() {
		return user.toString() + " - " + codice + " - ";
	}
}

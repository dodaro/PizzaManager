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
@Table(name = "payments")
public class Payment implements Serializable {

	private static final long serialVersionUID = 7166272043751535111L;

	private static final int NO_ID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@Column(name = "saved", nullable = false)
	private boolean saved;

	@Column(name = "paid", nullable = false)
	private boolean paid;

	public Payment() {
		id = NO_ID;
		saved = false;
		paid = false;
		user = new User();
	}

	public Payment(boolean saved, boolean paid, User user) {
		this.id = NO_ID;
		this.saved = saved;
		this.paid = paid;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean payed) {
		this.paid = payed;
	}
}

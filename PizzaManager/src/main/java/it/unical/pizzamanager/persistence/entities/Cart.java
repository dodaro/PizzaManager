package it.unical.pizzamanager.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "carts")
@SequenceGenerator(name = "cartsGenerator", sequenceName = "carts_sequence", initialValue = 1)
public class Cart implements Serializable {

	private static final long serialVersionUID = 2643239163189575110L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartsGenerator")
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "user")
	private User user;

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<OrderItem> orderItems;

	public Cart() {
		id = DatabaseHandler.NO_ID;
		user = null;
		orderItems = new ArrayList<OrderItem>();
	}
	
	public Cart(User user) {
		this.id = DatabaseHandler.NO_ID;
		this.user = user;
		this.orderItems = new ArrayList<OrderItem>();
	}
	
	public Cart(User user,ArrayList<OrderItem> orderItems){
		this.id=DatabaseHandler.NO_ID;
		this.user=user;
		this.orderItems=orderItems;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

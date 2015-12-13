package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "user")
	private String user;

	@OneToMany(mappedBy = "theorder", fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> orderItems;

	public Order() {
		this("");
	}

	public Order(String user) {
		this.id = -1;
		this.user = user;
		this.orderItems = new ArrayList<>();
	}

	public Order(String user, List<OrderItem> orderItems) {
		this.id = -1;
		this.user = user;
		this.orderItems = orderItems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

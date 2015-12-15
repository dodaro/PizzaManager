package it.unical.pizzamanager.persistence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "order_Items")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="orderType",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="orderItem")
@SequenceGenerator(name = "order_ItemsGenerator", sequenceName = "order_ItemsSequence", initialValue = 1)
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2048189882587093175L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_ItemsGenerator")
	private Integer id;

	
	@Column(name="cost")
	private Double cost;
	// instead of ha references add a boolean modified and the cost of item ordered so we can get cost of an order directly with a join 
	// without ask to table price, so ingredient cannot be ordered but a menu or a pizza can be modified cost is evalueted application side and stored in database at purchace time
	
	public OrderItem() {
		id=DatabaseHandler.NO_ID;
		cost=0.0;
	}

	public OrderItem(Double cost){
		id=DatabaseHandler.NO_ID;
		this.cost=cost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
}

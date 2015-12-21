package it.unical.pizzamanager.persistence.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("booking_delivery")
public class BookingDelivery extends Booking {

	private static final long serialVersionUID = -8241567483648220533L;

	@Temporal(TemporalType.TIME)
	@Column(name = "estimated_delivery_time")
	private Date estimatedDeliveryTime;

	@OneToOne
	@JoinColumn(name = "delivery_address")
	private Address deliveryAddress;

	public BookingDelivery() {
		super();
		this.deliveryAddress = new Address();
		this.estimatedDeliveryTime = new Date();
	}

	public BookingDelivery(Date date, Date time, Boolean confirmed, Person person, Payment payment,
			Integer priority, Date estimatedDeliveryTime, Address deliveryAddress,
			ArrayList<OrderItem> orderItems, Pizzeria pizzeria) {
		super(date, time, confirmed, person, payment, priority, orderItems, pizzeria);
		this.deliveryAddress = deliveryAddress;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public Address getAddressToDelivery() {
		return deliveryAddress;
	}

	public void setAddressToDelivery(Address addressToDelivery) {
		this.deliveryAddress = addressToDelivery;
	}
}

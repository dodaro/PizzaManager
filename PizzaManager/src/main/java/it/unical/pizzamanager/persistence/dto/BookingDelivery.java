package it.unical.pizzamanager.persistence.dto;

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
		this.deliveryAddress = null;
		this.estimatedDeliveryTime = new Date();
	}

	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}

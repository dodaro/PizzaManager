package it.unical.pizzamanager.persistence.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("booking_delivery")
public class BookingDelivery extends Booking {

	private static final long serialVersionUID = -8241567483648220533L;

	@OneToOne
	@JoinColumn(name = "delivery_address")
	private Address deliveryAddress;

	public BookingDelivery() {
		super();
		this.deliveryAddress = null;
	}

	public BookingDelivery(Date date, Date time, Boolean confirmed, Integer priority, Address deliveryAddress/*, Date estimatedDeliveryTime */) {
		super(date, time, confirmed, priority);
		this.deliveryAddress = deliveryAddress;
	}
	
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}

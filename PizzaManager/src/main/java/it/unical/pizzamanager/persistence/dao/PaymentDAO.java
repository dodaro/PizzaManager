package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import it.unical.pizzamanager.persistence.dto.Payment;

public interface PaymentDAO {
	public void delete(Payment payment);

	public List<Payment> getAllPayments();

	public List<Payment> getSaved(boolean saved);

	public Payment getPayment(Integer i);

	public void create(Payment payment);

	public Long numberOfPayment();

	public void update(Payment payment);
}

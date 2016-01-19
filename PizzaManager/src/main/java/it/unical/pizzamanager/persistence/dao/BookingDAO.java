package it.unical.pizzamanager.persistence.dao;


import java.util.Date;
import java.util.List;

import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

public interface BookingDAO {

	public void create(Booking booking);
	
	public void delete(Booking booking);
	
	public void update(Booking booking);
	
	public Booking getBooking(Integer id);
	
	public List<Booking> BookingInYear(Pizzeria pizzeria,Date date);
	
	public List<Booking> BookingInMonths(Pizzeria pizzeria,Date date);
	
	public Integer NumberOfBookingInAYearForType(Pizzeria pizzeria,Date date, String type);
	
	public Integer NumberOfBookingInAMonthsForType(Pizzeria pizzeria,Date date, String type);
	
	
	public List<Booking> getBookingList();
	
	public List<BookingPizzeriaTable> getOnlyBookingTablePizzeriaList();
	
	public List<BookingTakeAway> getOnlyBookingTakeAwayList();
	
	public List<BookingDelivery> getOnlyBookingDeliveryList();

	public List<Booking> getBookingsFromPizzeria(Pizzeria pizzeria);

	public List<Booking> getUserBookings(User user);
	
	
	//CHIEDERE A DODARO SE LE QUERY QUI GIÙ SERVONO O SE BASTA FARE LE SEGUENTI RICERCHE DIRETTAMENTE DA PIZZERIA (operando sulla lista di booking)
	
	/*//ricerca prenotazione per data
	public List<Booking> get(Date date);
			
	//ricerca prenotazione per data e ora
	public List<Booking> get(Date date, Date time);
			
	//ricerca prenotazione per data ora e priorità
	public List<Booking> get(Date date, Date time, Integer priority);
			
	//ricerca prenotazione per data e priorità
	public List<Booking> get(Date date, Integer priority);*/
	
	/*
	 * IMPORTANTE :MI CHIEDO a questo punto: che utilità ha la tabella Order? 
	   Non può essere Booking ad avere direttamente una lista di orderItem??
	   
	   DIFATTI  cos'è una prenotazione? un insieme di order item ritirate nella stessa data alla stessa ora.
	 */


}

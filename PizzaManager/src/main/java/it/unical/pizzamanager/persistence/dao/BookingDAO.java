package it.unical.pizzamanager.persistence.dao;


import java.util.List;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingTablePizzeria;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;

public interface BookingDAO {

	public void create(Booking booking);
	
	public void delete(Booking booking);
	
	public void update(Booking booking);
	
	public Booking getBooking(Integer id);
	
	public List<Booking> getBookingList();
	
	public List<BookingTablePizzeria> getOnlyBookingTablePizzeriaList();
	
	public List<BookingTakeAway> getOnlyBookingTakeAwayList();
	
	public List<BookingDelivery> getOnlyBookingDeliveryList();
	
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

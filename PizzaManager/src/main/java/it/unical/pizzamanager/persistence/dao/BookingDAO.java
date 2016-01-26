package it.unical.pizzamanager.persistence.dao;


import java.util.Date;
import java.util.List;

import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.User;

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

	public List<Booking> getActiveUserBookings(User user);

	public List<Booking> getOrderedBookings(Pizzeria pizzeria);
	
	public List<Booking> getBookingListFromDataAndPizzeria(Pizzeria pizzeria, Date date);
	
	public List<Booking> getAllBookingListFromData(Date date);
}

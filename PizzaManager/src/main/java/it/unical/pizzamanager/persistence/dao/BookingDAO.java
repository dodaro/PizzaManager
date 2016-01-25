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

	public List<Booking> getActiveUserBookings(User user);

	public List<Booking> getOrderedBookings(Pizzeria pizzeria);
	
	public List<Booking> getBookingListFromDataAndPizzeria(Pizzeria pizzeria, Date date);
	
	public List<Booking> getAllBookingListFromData(Date date);
}

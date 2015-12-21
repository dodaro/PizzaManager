package it.unical.pizzamanager.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingTablePizzeria;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;

public class BookingDAOImpl implements BookingDAO {

	private DatabaseHandler databaseHandler;

	public BookingDAOImpl() {
		this.databaseHandler = null;
	}

	@Override
	public void create(Booking booking) {
		databaseHandler.create(booking);
	}

	@Override
	public void delete(Booking booking) {
		databaseHandler.delete(booking);
	}

	@Override
	public void update(Booking booking) {
		databaseHandler.update(booking);
	}

	@Override
	public Booking getBooking(Integer id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingList() {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking";
		Query query = session.createQuery(queryString);
		List<Booking> bookings = (List<Booking>) query.list();

		session.close();
		return bookings;
	}

	// TODO verificare se la query su type funziona

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingTablePizzeria> getOnlyBookingTablePizzeriaList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where type = 'booking_table'";
		Query query = session.createQuery(queryString);
		List<BookingTablePizzeria> bookings = (List<BookingTablePizzeria>) query.list();
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingTakeAway> getOnlyBookingTakeAwayList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where type = 'booking_takeAway'";
		Query query = session.createQuery(queryString);
		List<BookingTakeAway> bookings = (List<BookingTakeAway>) query.list();
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingDelivery> getOnlyBookingDeliveryList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where type = 'booking_delivery'";
		Query query = session.createQuery(queryString);
		List<BookingDelivery> bookings = (List<BookingDelivery>) query.list();
		session.close();
		return bookings;
	}
	
	
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
}

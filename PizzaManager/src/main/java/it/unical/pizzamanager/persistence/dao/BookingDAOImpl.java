package it.unical.pizzamanager.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

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
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where id = :id ";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);

		Booking booking = (Booking) query.uniqueResult();
		booking.getOrderItems().size();
		session.close();
		return booking;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingsFromPizzeria(Pizzeria pizzeria) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where pizzeria = :pizzeria ";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);

		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}

		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getUserBookings(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where user = :user ";
		Query query = session.createQuery(queryString);
		query.setParameter("user", user);

		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}

		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingList() {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking";
		Query query = session.createQuery(queryString);
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}

		session.close();
		return bookings;
	}

	// TODO verificare se la query su type funziona

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingPizzeriaTable> getOnlyBookingTablePizzeriaList() {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where type = 'booking_table'";
		Query query = session.createQuery(queryString);
		List<BookingPizzeriaTable> bookings = (List<BookingPizzeriaTable>) query.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> BookingInYear(Pizzeria pizzeria, Date date) {

		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where pizzeria = :pizzeria and to_char(completionDate,'YYYY') = :date";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("date", new SimpleDateFormat("YYYY").format(date));
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> BookingInMonths(Pizzeria pizzeria, Date date) {
		// TODO Auto-generated method stub
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where pizzeria = :pizzeria and to_char(completionDate,'YYYY/MM') = :date";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("date", new SimpleDateFormat("YYYY/MM").format(date));
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}
		session.close();
		return bookings;
	}

	@Override
	public Integer NumberOfBookingInAYearForType(Pizzeria pizzeria, Date date, String type) {

		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "select count(*) from Booking where pizzeria = :pizzeria and to_char(completionDate,'YYYY') = :date and type= :type";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("date", new SimpleDateFormat("YYYY").format(date));
		query.setParameter("type", type);
		Long number = (Long) query.uniqueResult();
		session.close();
		return (int) (long) number;
	}

	@Override
	public Integer NumberOfBookingInAMonthsForType(Pizzeria pizzeria, Date date, String type) {

		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "select count(*) from Booking where pizzeria = :pizzeria and to_char(completionDate,'YYYY/MM') = :date and type= :type";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("date", new SimpleDateFormat("YYYY/MM").format(date));
		query.setParameter("type", type);
		Long number = (Long) query.uniqueResult();
		session.close();
		return (int) (long) number;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getActiveUserBookings(User user) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where user = :user and completion_date = null";
		Query query = session.createQuery(queryString);
		query.setParameter("user", user);

		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
			booking.getPayment();
		}
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getOrderedBookings(Pizzeria pizzeria) {
		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where pizzeria = :pizzeria and confirmed = :confirmed ORDER BY priority DESC";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("confirmed", true);
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingListFromDataAndPizzeria(Pizzeria pizzeria, Date date) {

		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where pizzeria = :pizzeria and to_char(date,'YYYY/MM') = :date";
		Query query = session.createQuery(queryString);
		query.setParameter("pizzeria", pizzeria);
		query.setParameter("date", new SimpleDateFormat("YYYY/MM").format(date));
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}
		session.close();
		return bookings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getAllBookingListFromData(Date date) {

		Session session = databaseHandler.getSessionFactory().openSession();

		String queryString = "from Booking where to_char(date,'YYYY/MM') = :date";
		Query query = session.createQuery(queryString);
		query.setParameter("date", new SimpleDateFormat("YYYY/MM").format(date));
		List<Booking> bookings = (List<Booking>) query.list();
		for (Booking booking : bookings) {
			booking.getOrderItems().size();
		}
		session.close();
		return bookings;
	}
}

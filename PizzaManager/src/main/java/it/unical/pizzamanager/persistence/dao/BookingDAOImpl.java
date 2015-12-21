package it.unical.pizzamanager.persistence.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingTablePizzeria;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.RelationTableBookingTablePizzeria;

public class BookingDAOImpl implements BookingDAO{

	private DatabaseHandler databaseHandler;
	
	
	
	public BookingDAOImpl() {
		// TODO Auto-generated constructor stub
		this.databaseHandler=null;
	}
	@Override
	public void create(Booking booking) {
		// TODO Auto-generated method stub
		databaseHandler.create(booking);
	}

	@Override
	public void delete(Booking booking) {
		// TODO Auto-generated method stub
		databaseHandler.delete(booking);
	}

	@Override
	public void update(Booking booking) {
		// TODO Auto-generated method stub
		databaseHandler.update(booking);
	}

	@Override
	public Booking getBooking(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingList() {
		// TODO Auto-generated method stub
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
	
	//TODO verificare se la query su type funziona
	
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

//TODO nel caso in cui DODARO dice che servono quei metodi mi basta sostituire order con booking nelle sottostanti funzioni
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Order> get(Date date) {
//		// TODO Auto-generated method stub
//		Session session = databaseHandler.getSessionFactory().openSession();
//		
//		String queryString = "from Order where date = :date_order";
//		Query query = session.createQuery(queryString);
//		query.setParameter("date_order", date);
//		List<Order> orders = (List<Order>) query.list();
//		//TODO 
//		/*if(orders!=null){
//			for (Order o : orders) {
//				o.getOrderItems().size();
//			}
//		}*/
//		session.close();
//		return orders;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Order> get(Date date, Date time){
//		
//		Session session = databaseHandler.getSessionFactory().openSession();
//		
//		String queryString = "from Order where date = :date_order and time = :time_order";
//		Query query = session.createQuery(queryString);
//		query.setParameter("date_order", date);
//		query.setParameter("time_order", time);
//		List<Order> orders = (List<Order>) query.list();
//		//TODO 
//		/*if(orders!=null){
//			for (Order o : orders) {
//				o.getOrderItems().size();
//			}
//		}*/
//		session.close();
//		return orders;
//	}
//		
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Order> get(Date date, Date time, Integer priority){
//		
//		Session session = databaseHandler.getSessionFactory().openSession();
//		
//		String queryString = "from Order where date = :date_order and time = :time_order and priority = :priority_order";
//		Query query = session.createQuery(queryString);
//		query.setParameter("date_order", date);
//		query.setParameter("time_order", time);
//		query.setParameter("priority_order", priority);
//		List<Order> orders = (List<Order>) query.list();
//		//TODO 
//		/*if(orders!=null){
//			for (Order o : orders) {
//				o.getOrderItems().size();
//			}
//		}*/
//		
//		session.close();
//		return orders;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Order> get(Date date, Integer priority){
//	
//		Session session = databaseHandler.getSessionFactory().openSession();
//		
//		String queryString = "from Order where date = :date_order and priority = :priority_order";
//		Query query = session.createQuery(queryString);
//		query.setParameter("date_order", date);
//		query.setParameter("priority_order", priority);
//		List<Order> orders = (List<Order>) query.list();
//		//TODO 
//		/*if(orders!=null){
//			for (Order o : orders) {
//				o.getOrderItems().size();
//			}
//		}*/
//		
//		session.close();
//		return orders;
//	}
	
	
}

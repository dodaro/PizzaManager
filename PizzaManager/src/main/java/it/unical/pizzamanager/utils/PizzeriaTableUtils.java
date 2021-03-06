package it.unical.pizzamanager.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.PizzeriaTable;
import it.unical.pizzamanager.persistence.entities.RelationBookingTablePizzeriaTable;

public class PizzeriaTableUtils {

	public static List<PizzeriaTable> getAvailableTables(Pizzeria pizzeria, Date date, Date time, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		List<Booking> bookings = bookingDAO.getBookingListFromDataAndPizzeria(pizzeria, date);
		
		List<PizzeriaTable> freeTable = new ArrayList<>();
		for (PizzeriaTable pizzeriaTable : pizzeria.getTables()) {
			freeTable.add(pizzeriaTable);
		}
		for (Booking booking : bookings) {
			if (booking instanceof BookingPizzeriaTable) {
				if (Math.abs((booking.getDate().getTime()+booking.getTime().getTime()) - (date.getTime()+time.getTime())) < 3600000) {
					for (RelationBookingTablePizzeriaTable t : ((BookingPizzeriaTable) booking).getTableBooking()){
						Iterator<PizzeriaTable> i = freeTable.listIterator();
						while (i.hasNext()) {
						   PizzeriaTable table = i.next(); 
						   if (t.getPizzeriaTable().getId()==table.getId()) 
							   i.remove();
						}
					}
				}
			}
		}
		
		
		return freeTable;
	}
}

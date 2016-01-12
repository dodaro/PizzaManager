package it.unical.pizzamanager.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;

public class BookingUtils {

	public static Booking createBookingFromBookingModel(BookingModel model, WebApplicationContext context) {
		
		Booking booking = null;
		
		switch (model.getType()) {
		case "takeAway":
			booking=new BookingTakeAway();
			break;
		case "table":
			booking=new BookingPizzeriaTable();
			
			break;
		case "delivery":
			booking=new BookingDelivery();
			break;
		default:
			break;
		}
		
		
		//creo quanto server
		
		
		return booking;
	}
}

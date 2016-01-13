package it.unical.pizzamanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dto.Address;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;

public class BookingUtils {

	private static String TAKE_AWAY="takeAway";
	private static String DELIVERY="delivery";
	private static String TABLE="table";
	
	public static Booking createBookingFromBookingModel(BookingModel model, Pizzeria pizzeria, WebApplicationContext context) {
		
		Integer idBooking=model.getId();
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		bookingDAO.delete(bookingDAO.getBooking(idBooking));
		
		
		/*switch (model.getType()) {
		case "takeAway":
			//posso editare quello corrente
			BookingTakeAway bookingTakeAway=(BookingTakeAway)createAndInitInformationBooking(model);
			
			
			break;
		case "table":
			BookingPizzeriaTable bookingTable=new BookingPizzeriaTable();
			
			break;
		case "delivery":
			BookingDelivery bookingDelivery=new BookingDelivery();
			break;
		default:
			break;
		}*/
		
		Booking booking=createAndInitInformationBooking(model,pizzeria,model.getType());
		//creo quanto server
		
		bookingDAO.create(booking);
		return booking;
	}
	
	
	protected static Booking createAndInitInformationBooking(BookingModel model,Pizzeria pizzeria,String type){
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-M-yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
		Date date=null;
		Date time=null;
		try {
			date = sdfDate.parse(model.getDate());
			time = sdfTime.parse(model.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String name=model.getName();
		//User user= dao.get(model.getUserId()); 
		Boolean confirmed=false;
	
		
		//IMPORTANTE :payment è già nullo nel costruttore!!
		if(type.equals(DELIVERY)){
			//set delivery address
			Address deliveryAddress=null;
			//deliveryAddress.setAccount(user);
			//deliveryAddress.setCity(model.getAddress);
			BookingDelivery bookingDelivery=new BookingDelivery(date,time,confirmed,Booking.PRIORITY_DEFAULT,deliveryAddress);
			if(model.getId()!=-1)
				bookingDelivery.setId(model.getId());
			bookingDelivery.setPizzeria(pizzeria);
			
			return bookingDelivery;
		}
		else if(type.equals(TABLE)){
			List<RelationBookingTablePizzeriaTable> tableBooking=null;
			BookingPizzeriaTable bookingPizzeriaTable=new BookingPizzeriaTable(date,time,confirmed,Booking.PRIORITY_DEFAULT,tableBooking);
			if(model.getId()!=-1)
				bookingPizzeriaTable.setId(model.getId());
			bookingPizzeriaTable.setPizzeria(pizzeria);
			
			return bookingPizzeriaTable;
		}
		else if(type.equals(TAKE_AWAY)){
			BookingTakeAway bookingTakeAway =new BookingTakeAway(date,time,confirmed,Booking.PRIORITY_DEFAULT);
			if(model.getId()!=-1)
				bookingTakeAway.setId(model.getId());
			bookingTakeAway.setPizzeria(pizzeria);
	
			return bookingTakeAway;
		}
		return null;
		
	}
	
	private static void setPizzaOrderBooking(Booking booking, BookingModel model){
		
		/*
		PizzaOrderItem pizzaOrder1 = new PizzaOrderItem();
		// MANCA L'UTENTE O IL NOME DI CHI HA PRENOTATO
		pizzaOrder1.setPizzeria_pizza(pizzeriaDAO.getAll().get(0).getPizzasPriceList().get(0));
		pizzaOrder1.setModified(false);
		pizzaOrder1.setNumber(3);
		pizzaOrder1.setGlutenFree(PizzaOrderItem.NO);
		pizzaOrder1.setSize(PizzaOrderItem.SMALL);
		order.create(pizzaOrder1);
		*/
	}
	
	private void setBeverageOrderBooking(Booking booking, BookingModel model){
		
	}

}

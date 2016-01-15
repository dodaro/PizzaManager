package it.unical.pizzamanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.models.PizzaModel;
import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Address;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.dto.User;

public class BookingUtils {

	private static String TAKE_AWAY="takeAway";
	private static String DELIVERY="delivery";
	private static String TABLE="table";
	
	public static Booking calculateBill(Booking booking, Pizzeria pizzeria, WebApplicationContext context){
		return null;
	}
	
	public static Booking createBookingFromBookingModel(BookingModel model, Pizzeria pizzeria, WebApplicationContext context) {
		
		Integer idBooking=model.getId();
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		bookingDAO.delete(bookingDAO.getBooking(idBooking));
		//il metodo già mi crea sul database l'oggetto
		Booking booking=createAndInitInformationBooking(model,pizzeria,model.getType(),context);
		//creo quanto server
		return booking;
	}
	
	
	private static Booking createAndInitInformationBooking(BookingModel model,Pizzeria pizzeria,String type, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/M/yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
		Date date=null;
		Date time=null;
		try {
			date = sdfDate.parse(model.getDate());
			time = sdfTime.parse(model.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String underTheNameOf=null;
		if(model.getUnderTheNameOf()!=null || model.getUnderTheNameOf().equals(""))
			underTheNameOf=model.getUnderTheNameOf();
		User user=null; 
		if(model.getUser()!=null || !model.getUser().equals("")){
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");	
			user=userDAO.get(model.getUser());
		}
		
		
		Boolean confirmed=false;
	
		
		//IMPORTANTE :payment è già nullo nel costruttore!!
		if(type.equals(DELIVERY)){
			//set delivery address
			Address deliveryAddress=null;
			AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
			Address searchedAddress=addressDAO.get(model.getAddress().getCity(), model.getAddress().getStreet(),Integer.parseInt(model.getAddress().getNumber()));
			
			if(searchedAddress!=null){
				System.out.println(searchedAddress.getId());
				deliveryAddress=searchedAddress;
			}
			else{
				System.out.println("ugualeanull");
				deliveryAddress=new Address();
				deliveryAddress.setCity(model.getAddress().getCity());
				deliveryAddress.setStreet(model.getAddress().getStreet());
				deliveryAddress.setNumber(Integer.parseInt(model.getAddress().getNumber()));
				addressDAO.create(deliveryAddress);
			}
			//IN QUESTO CASO l'ACCOUNT NELL'ADDRESS COSA CI FACCIO?
			//deliveryAddress.setAccount(user);
			BookingDelivery bookingDelivery=new BookingDelivery(date,time,confirmed,Booking.PRIORITY_DEFAULT,deliveryAddress);
			//if(model.getId()!=-1)
				//bookingDelivery.setId(model.getId());
			bookingDelivery.setPizzeria(pizzeria);
			bookingDelivery.setUser(user);
			bookingDelivery.setBookerName(underTheNameOf);
			bookingDelivery.setOrderItems(new ArrayList<OrderItem>());
			bookingDAO.create(bookingDelivery);
			
			setPizzaOrderBooking(bookingDelivery, model, pizzeria, context);
			setBeverageOrderBooking(bookingDelivery, model, pizzeria, context);
			
			
			return bookingDelivery;
		}
		else if(type.equals(TABLE)){
			List<RelationBookingTablePizzeriaTable> tablesBooking=new ArrayList<>();
		
			BookingPizzeriaTable bookingPizzeriaTable=new BookingPizzeriaTable(date,time,confirmed,Booking.PRIORITY_DEFAULT,tablesBooking);
			bookingPizzeriaTable.setPizzeria(pizzeria);
			bookingPizzeriaTable.setUser(user);
			bookingPizzeriaTable.setBookerName(underTheNameOf);
			bookingPizzeriaTable.setOrderItems(new ArrayList<OrderItem>());
			bookingDAO.create(bookingPizzeriaTable);
			
			for (int i = 0; i < pizzeria.getTables().size(); i++) {
				for (int j = 0; j < model.getTables().size(); j++) {
					if(pizzeria.getTables().get(i).getNumber()==model.getTables().get(j)){
						tablesBooking.add(new RelationBookingTablePizzeriaTable(pizzeria.getTables().get(i),bookingPizzeriaTable));						
					}
				}
			}
			bookingPizzeriaTable.setTableBooking(tablesBooking);
			bookingDAO.update(bookingPizzeriaTable);
			
			setPizzaOrderBooking(bookingPizzeriaTable, model, pizzeria, context);
			setBeverageOrderBooking(bookingPizzeriaTable, model, pizzeria, context);
			
			return bookingPizzeriaTable;
		}
		else if(type.equals(TAKE_AWAY)){
			BookingTakeAway bookingTakeAway =new BookingTakeAway(date,time,confirmed,Booking.PRIORITY_DEFAULT);
			bookingTakeAway.setPizzeria(pizzeria);
			bookingTakeAway.setUser(user);
			bookingTakeAway.setBookerName(underTheNameOf);
			bookingTakeAway.setOrderItems(new ArrayList<OrderItem>());
			bookingDAO.create(bookingTakeAway);
			
			setPizzaOrderBooking(bookingTakeAway, model, pizzeria, context);
			setBeverageOrderBooking(bookingTakeAway, model, pizzeria, context);
	
			return bookingTakeAway;
		}
		return null;
		
	}
	
	private static void setPizzaOrderBooking(Booking booking, BookingModel model,Pizzeria pizzeria, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		
		for (int i = 0; i < model.getPizzas().size(); i++) {
			PizzaModel pizzaModel=model.getPizzas().get(i);
			for (int j = 0; j < pizzeria.getPizzasPriceList().size(); j++) {
				RelationPizzeriaPizza relation=pizzeria.getPizzasPriceList().get(j);
				if(relation.getPizza().getName().equals(pizzaModel.getName())
				   /*&& relation.getGlutenFree().equals(pizzaModel.getGluten())*/
				   /*&& relation.getPizzaSize().toString().equalsIgnoreCase()*/){
					break;
				}
				
				PizzaOrderItem pizzaOrder = new PizzaOrderItem();
				pizzaOrder.setPizzeria_pizza(relation);
				
				if(pizzaModel.getIngredientsAdded().size()>0 || pizzaModel.getIngredientsRemoved().size()>0)
					pizzaOrder.setModified(true);
				else
					pizzaOrder.setModified(false);
				pizzaOrder.setNumber(pizzaModel.getNumber());
				//pizzaOrder.setGlutenFree(relation.getGlutenFree());
				//pizzaOrder.setSize(relation.getPizzaSize());
				orderDAO.create(pizzaOrder);
				
				
				for (int k = 0; k < pizzaModel.getIngredientsAdded().size(); k++) {
					
				}
				for (int k = 0; k < pizzaModel.getIngredientsRemoved().size(); k++) {
					
				}
				booking.getOrderItems().add(pizzaOrder);
				bookingDAO.update(booking);
				pizzaOrder.setBooking(booking);
				orderDAO.update(pizzaOrder);
			}
		}
		
		/*
		PizzaOrderItem pizzaOrder1 = new PizzaOrderItem();
		pizzaOrder1.setPizzeria_pizza(pizzeriaDAO.getAll().get(0).getPizzasPriceList().get(0));
		pizzaOrder1.setModified(false);
		pizzaOrder1.setNumber(3);
		pizzaOrder1.setGlutenFree(PizzaOrderItem.NO);
		pizzaOrder1.setSize(PizzaOrderItem.SMALL);
		order.create(pizzaOrder1);
		
		List<RelationPizzaOrderItemIngredient> ingredientsAdded = new ArrayList<>();
		ingredientsAdded.add(new RelationPizzaOrderItemIngredient(
				RelationPizzaOrderItemIngredient.ADDITION,
				pizzeriaDAO.getAll().get(0).getIngredientsPriceList().get(7).getIngredient(),
				pizzaOrder2));
		pizzaOrder2.setPizzaOrderIngredients(ingredientsAdded);
		
		booking.setOrderItems(orderItemsTable);
		bookingDAO.update(booking);
		
		pizzaOrder1.setBooking(takeAway);
		order.update(pizzaOrder1);
		*/
	}
	
	private static void setBeverageOrderBooking(Booking booking, BookingModel model,Pizzeria pizzeria, WebApplicationContext context){
		/*
		 BeverageOrderItem beverageOrder4 = new BeverageOrderItem();
		beverageOrder4.setPizzeria_beverage(pizzeriaDAO.getAll().get(0).getBeveragesPriceList().get(3));
		beverageOrder4.setNumber(2);
		orderDAO.create(beverageOrder4);
		
		booking.getOrderItems().add(pizzaOrder);
				bookingDAO.update(booking);
		
		orderDAO.update(beverageOrder1);
		 */
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		
		for (int i = 0; i < model.getBeverages().size(); i++) {
			for (int j = 0; j < pizzeria.getBeveragesPriceList().size(); j++) {
				if(pizzeria.getBeveragesPriceList().get(j).getBeverage().getId()==model.getBeverages().get(i).getId()){
					BeverageOrderItem beverageOrder = new BeverageOrderItem();
					beverageOrder.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(j));
					beverageOrder.setNumber(2);
					orderDAO.create(beverageOrder);
					
					booking.getOrderItems().add(beverageOrder);
					bookingDAO.update(booking);
			
					beverageOrder.setBooking(booking);
					orderDAO.update(beverageOrder);
				}
					
			}
		}
	}
	
	

}

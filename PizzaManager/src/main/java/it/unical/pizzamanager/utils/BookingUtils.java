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
import it.unical.pizzamanager.persistence.entities.Address;
import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.OrderItem;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationBookingTablePizzeriaTable;
import it.unical.pizzamanager.persistence.entities.RelationPizzaOrderItemIngredient;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.User;

public class BookingUtils {

	private static String TAKE_AWAY="takeAway";
	private static String DELIVERY="delivery";
	private static String TABLE="table";

	public static Booking calculateBill(Booking booking, Pizzeria pizzeria){
		Double bill=0.0;
		for (OrderItem order : booking.getOrderItems()) {
			bill+=order.getCost();
		}
		booking.setBill(bill);
		return booking;
	}
	
	public static Booking createBookingFromBookingModelAndSave(BookingModel model, Pizzeria pizzeria, WebApplicationContext context) {
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		//metodo già mi crea sul database l'oggetto
		Booking booking=createAndInitInformationBookingAndSave(model,pizzeria,model.getType(),context);
		//creo quanto server
		return booking;
	}
	
	
	private static Booking createAndInitInformationBookingAndSave(BookingModel model,Pizzeria pizzeria,String type, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");

		/*
		 * Nel caso in cui viene modificato un booking che prima era , ad esempio, di tipo bookingTakeAway, e voglio
		 * fare un update rendendolo di tipo bookingDelivery(fare l'update del discriminatore) non mi viene consentito da hibernate
		 * */
		if(model.getId()!=null){
			bookingDAO.delete(bookingDAO.getBooking(model.getId()));
		}
		
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
		
		
		Boolean confirmed=model.getConfirmed();
	
		
		//IMPORTANTE :payment è già nullo nel costruttore!!
		if(type.equals(DELIVERY)){
			//set delivery address
			Address deliveryAddress=null;
			AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
			Address searchedAddress=addressDAO.get(model.getAddress().getCity(), model.getAddress().getStreet(),Integer.parseInt(model.getAddress().getNumber()));
			
			if(searchedAddress!=null){
				deliveryAddress=searchedAddress;
			}
			else{
				deliveryAddress=new Address();
				deliveryAddress.setCity(model.getAddress().getCity());
				deliveryAddress.setStreet(model.getAddress().getStreet());
				deliveryAddress.setNumber(Integer.parseInt(model.getAddress().getNumber()));
				addressDAO.create(deliveryAddress);
			}
		
			BookingDelivery bookingDelivery=new BookingDelivery(date,time,confirmed,Booking.PRIORITY_DEFAULT,deliveryAddress);
			bookingDelivery.setPizzeria(pizzeria);
			bookingDelivery.setUser(user);
			bookingDelivery.setBookerName(underTheNameOf);
			bookingDelivery.setOrderItems(new ArrayList<OrderItem>());
			bookingDAO.create(bookingDelivery);
			
			setPizzaOrderBookingAndSave(bookingDelivery, model, pizzeria, context);
			setBeverageOrderBookingAndSave(bookingDelivery, model, pizzeria, context);
			bookingDelivery=(BookingDelivery)calculateBill(bookingDelivery, pizzeria);
			bookingDAO.update(bookingDelivery);
			
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
					if(pizzeria.getTables().get(i).getId()==model.getTables().get(j)){
						tablesBooking.add(new RelationBookingTablePizzeriaTable(pizzeria.getTables().get(i),bookingPizzeriaTable));						
					}
				}
			}
			bookingPizzeriaTable.setTableBooking(tablesBooking);
			bookingDAO.update(bookingPizzeriaTable);
			
			setPizzaOrderBookingAndSave(bookingPizzeriaTable, model, pizzeria, context);
			setBeverageOrderBookingAndSave(bookingPizzeriaTable, model, pizzeria, context);
			bookingPizzeriaTable=(BookingPizzeriaTable)calculateBill(bookingPizzeriaTable, pizzeria);
			bookingDAO.update(bookingPizzeriaTable);
			return bookingPizzeriaTable;
		}
		else if(type.equals(TAKE_AWAY)){
			BookingTakeAway bookingTakeAway =new BookingTakeAway(date,time,confirmed,Booking.PRIORITY_DEFAULT);
			bookingTakeAway.setPizzeria(pizzeria);
			bookingTakeAway.setUser(user);
			bookingTakeAway.setBookerName(underTheNameOf);
			bookingTakeAway.setOrderItems(new ArrayList<OrderItem>());
			bookingDAO.create(bookingTakeAway);
			
			//questi metodi salvano gia lo stato
			setPizzaOrderBookingAndSave(bookingTakeAway, model, pizzeria, context);
			setBeverageOrderBookingAndSave(bookingTakeAway, model, pizzeria, context);
			bookingTakeAway=(BookingTakeAway)calculateBill(bookingTakeAway, pizzeria);
			bookingDAO.update(bookingTakeAway);
			return bookingTakeAway;
		}
		return null;
		
	}
	
	private static void setPizzaOrderBookingAndSave(Booking booking, BookingModel model,Pizzeria pizzeria, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		
		for (int i = 0; i < model.getPizzas().size(); i++) {
			PizzaModel pizzaModel=model.getPizzas().get(i);			
			for (int j = 0; j < pizzeria.getPizzasPriceList().size(); j++) {
				
				RelationPizzeriaPizza relation=pizzeria.getPizzasPriceList().get(j);
				if(relation.getPizza().getName().equalsIgnoreCase(pizzaModel.getName())
				   && relation.getGlutenFree()==Boolean.parseBoolean(pizzaModel.getGlutenFree())
				   && relation.getPizzaSize().toString().equalsIgnoreCase(pizzaModel.getSize())){
					
					PizzaOrderItem pizzaOrder = new PizzaOrderItem();
					pizzaOrder.setPizzeria_pizza(relation);
					
					if(pizzaModel.getIngredientsAdded().size()>0 || pizzaModel.getIngredientsRemoved().size()>0){
						pizzaOrder.setModified(true);
						pizzaOrder.setPizzaOrderIngredients(new ArrayList<RelationPizzaOrderItemIngredient>());
					}
					else
						pizzaOrder.setModified(false);
						
					pizzaOrder.setNumber(pizzaModel.getNumber());
					orderDAO.create(pizzaOrder);
					
					
					for (int k = 0; k < pizzaModel.getIngredientsAdded().size(); k++) {
						Integer idIngredient=pizzaModel.getIngredientsAdded().get(k).getId();
						for (int l = 0; l < pizzeria.getIngredientsPriceList().size(); l++) {
							if(pizzeria.getIngredientsPriceList().get(l).getIngredient().getId()==idIngredient){
								pizzaOrder.getPizzaOrderIngredients().add(new RelationPizzaOrderItemIngredient(
										RelationPizzaOrderItemIngredient.ADDITION,
										pizzeria.getIngredientsPriceList().get(l).getIngredient(),pizzaOrder));								
							}
						}
					}
					for (int k = 0; k < pizzaModel.getIngredientsRemoved().size(); k++) {
						Integer idIngredient=pizzaModel.getIngredientsRemoved().get(k).getId();
						for (int l = 0; l < pizzeria.getIngredientsPriceList().size(); l++) {
							if(pizzeria.getIngredientsPriceList().get(l).getIngredient().getId()==idIngredient){
								pizzaOrder.getPizzaOrderIngredients().add(new RelationPizzaOrderItemIngredient(
										RelationPizzaOrderItemIngredient.REMOVAL,
										pizzeria.getIngredientsPriceList().get(l).getIngredient(),pizzaOrder));								
							}
						}
					}
					
					pizzaOrder=OrderItemUtils.setPizzaOrderCost(pizzaOrder, pizzeria);
					orderDAO.update(pizzaOrder);
					booking.getOrderItems().add(pizzaOrder);
					bookingDAO.update(booking);
					pizzaOrder.setBooking(booking);
					orderDAO.update(pizzaOrder);
					break;
				}
				
			}
		}
	}
	
	private static void setBeverageOrderBookingAndSave(Booking booking, BookingModel model,Pizzeria pizzeria, WebApplicationContext context){
		
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		OrderItemDAO orderDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		
		for (int i = 0; i < model.getBeverages().size(); i++) {
			for (int j = 0; j < pizzeria.getBeveragesPriceList().size(); j++) {
				if(pizzeria.getBeveragesPriceList().get(j).getBeverage().getId()==model.getBeverages().get(i).getId()){
					BeverageOrderItem beverageOrder = new BeverageOrderItem();
					beverageOrder.setPizzeria_beverage(pizzeria.getBeveragesPriceList().get(j));
					beverageOrder.setNumber(Integer.parseInt(model.getBeverages().get(i).getNumber()));
					beverageOrder=OrderItemUtils.setBeverageOrderCost(beverageOrder, pizzeria);
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

package it.unical.pizzamanager.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaStatisticsController {
	@Autowired
	private WebApplicationContext context;

	
	@RequestMapping(value = "/pizzeriastatistics", method = RequestMethod.GET)
	public String pizzeriaStatistics(Model model, HttpSession session) throws ParseException {
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		model.addAttribute("pizzeria", pizzeria);
		return "pizzeriastatistics";
	}
	
	
	@RequestMapping(value = "/pizzeriastatisticsAjax", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, List<Integer>>> processAJAXRequest(HttpServletRequest request, Model model,HttpSession session,@RequestParam("action") String actionString,@RequestParam("date") String dateString) {
		
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		
		
		List<HashMap<String, List<Integer>>> list=new ArrayList<HashMap<String,List<Integer>>>();
		SimpleDateFormat sdfDate =null;
			if(actionString.equals("yearAction")){
				sdfDate=new SimpleDateFormat("yyyy");
			}
			else if(actionString.equals("monthAction")){
				sdfDate=new SimpleDateFormat("MM/yyyy");
			}
		Date date=null;
			try {
				date = sdfDate.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		HashMap<String,List<Integer>> hashTablePizzas=new HashMap<>();
		HashMap<String,List<Integer>> hashTableBooking=new HashMap<>();
		
		HashSet<Pizza> pizzasSet = new HashSet<Pizza>();
		for (RelationPizzeriaPizza relation : pizzeria.getPizzasPriceList()) {
			pizzasSet.add(relation.getPizza());
		}
		List<Pizza> pizzas=new ArrayList<>();
		pizzas.addAll(pizzasSet);
		
			if(actionString.equals("yearAction")){
				List<Booking> bookings = bookingDAO.BookingInYear(pizzeria, date);
				for (int i = 0; i < pizzas.toArray().length; i++) {
					List<Integer> yearPerMonths = getOrderItemNumberForMonth(bookings, pizzas.get(i));
					hashTablePizzas.put(pizzas.get(i).getName(), yearPerMonths);
				}
				
				List<Integer> listDelivery=new ArrayList<>();
				listDelivery.add(bookingDAO.NumberOfBookingInAYearForType(pizzeria, date,"booking_delivery"));
				List<Integer> listTakeAway=new ArrayList<>();
				listTakeAway.add(bookingDAO.NumberOfBookingInAYearForType(pizzeria, date,"booking_takeaway"));
				List<Integer> listTable=new ArrayList<>();
				listTable.add(bookingDAO.NumberOfBookingInAYearForType(pizzeria, date,"booking_table"));
				
				hashTableBooking.put("delivery",listDelivery);
				hashTableBooking.put("takeAway",listTakeAway);
				hashTableBooking.put("table",listTable);
				
			}
			else if(actionString.equals("monthAction")){
				List<Booking> bookings = bookingDAO.BookingInMonths(pizzeria, date);
				for (int i = 0; i < pizzas.toArray().length; i++) {
					List<Integer> monthPerDays = getOrderItemNumberForDay(bookings, pizzas.get(i));
					hashTablePizzas.put(pizzas.get(i).getName(), monthPerDays);
				}
				
				List<Integer> listDelivery=new ArrayList<>();
				listDelivery.add(bookingDAO.NumberOfBookingInAMonthsForType(pizzeria, date,"booking_delivery"));
				List<Integer> listTakeAway=new ArrayList<>();
				listTakeAway.add(bookingDAO.NumberOfBookingInAMonthsForType(pizzeria, date,"booking_takeaway"));
				List<Integer> listTable=new ArrayList<>();
				listTable.add(bookingDAO.NumberOfBookingInAMonthsForType(pizzeria, date,"booking_table"));
				
				hashTableBooking.put("delivery",listDelivery);
				hashTableBooking.put("takeAway",listTakeAway);
				hashTableBooking.put("table",listTable);
			}
			
			
		list.add(hashTablePizzas);
		list.add(hashTableBooking);
		return list;
	}
	
	@SuppressWarnings("deprecation")
	private List<Integer> getOrderItemNumberForMonth(List<Booking> bookings,Pizza pizza){
		List<Integer> numberPerMonth=new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			numberPerMonth.add(0);
		}
		
		for (Booking booking : bookings) {
			for (int i = 0; i < booking.getOrderItems().size(); i++) {
				Integer indexMonth=booking.getCompletionDate().getMonth();
				if(booking.getOrderItems().get(i) instanceof PizzaOrderItem){
					PizzaOrderItem pizzaOrder=(PizzaOrderItem)booking.getOrderItems().get(i);
					if(pizzaOrder.getPizzeria_pizza().getPizza().getName().equals(pizza.getName())){
						numberPerMonth.set(indexMonth, numberPerMonth.get(indexMonth)+1);
					}
				}
			}
		}
		
		return numberPerMonth;
	}
	
	
	private List<Integer> getOrderItemNumberForDay(List<Booking> bookings,Pizza pizza){
		List<Integer> numberPerDay=new ArrayList<>();
		for (int i = 0; i < 31; i++) {
			numberPerDay.add(0);
		}
		
		for (Booking booking : bookings) {
			for (int i = 0; i < booking.getOrderItems().size(); i++) {
				String day=new SimpleDateFormat("DD").format(booking.getCompletionDate());
				Integer indexDay=Integer.parseInt(day);
				if(booking.getOrderItems().get(i) instanceof PizzaOrderItem){
					PizzaOrderItem pizzaOrder=(PizzaOrderItem)booking.getOrderItems().get(i);
					if(pizzaOrder.getPizzeria_pizza().getPizza().getName().equals(pizza.getName())){
						numberPerDay.set(indexDay, numberPerDay.get(indexDay)+1);
					}
				}
			}
		}
		
		return numberPerDay;
	}
}

package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.BeverageDAO;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.MenuDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

@Controller
public class PizzeriaLiveOrderController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaLiveOrderController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzerialiveorder", method = RequestMethod.GET)
	public String pizzeriaLiveOrder(Model model) {
		logger.info("Live order requested");

		//ogni qualvolta si riavvia l'applicazione il database viene azzerato
		PizzaDAO pizzaDao = (PizzaDAO) context.getBean("pizzaDAO");
		//BeverageDAO beverageDao = (BeverageDAO) context.getBean("beverageDAO");
		//MenuDAO menuDao = (MenuDAO) context.getBean("menuDAO");
		
		List<Pizza> pizzas = (List<Pizza>) pizzaDao.getAll();
		
		PizzeriaDAO pizzeriaDao=(PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria=pizzeriaDao.getAll().get(0);
		
		

		
		
		model.addAttribute("pizzasList", pizzas);
		//model.addAttribute("beveragesList", beverages);
		//model.addAttribute("menusList", menus);
		//model.addAttribute("pizzeria", pizzeria);
		return "pizzerialiveorder";
	}
	
	/*@RequestMapping(value = "/pizzerialiveorderPizzas", method = RequestMethod.GET)
	public @ResponseBody List<Pizza> processAJAXRequest(HttpServletRequest request, Model model) {
		PizzaDAO dao = (PizzaDAO) context.getBean("pizzaDAO");
		
		List<Pizza> pizzas = (List<Pizza>) dao.getAll();
		System.out.println("qua ci sono");
		//return "{ 'ciao':'bello'}";
		return pizzas;
	}*/
	
	@RequestMapping(value = "/pizzerialiveorderPizzas", method = RequestMethod.GET)
	public @ResponseBody Pizzeria processAJAXRequest(HttpServletRequest request, Model model) {
		PizzaDAO dao = (PizzaDAO) context.getBean("pizzaDAO");
		
		PizzeriaDAO pizzeriaDao=(PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria=pizzeriaDao.getAll().get(0);
		//return "{ 'ciao':'gay'}";
		return pizzeria;
	}
	
}
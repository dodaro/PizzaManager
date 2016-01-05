package it.unical.pizzamanager.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.unical.pizzamanager.persistence.dao.BeverageDAO;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.MenuDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
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
		//PizzaDAO pizzaDao = (PizzaDAO) context.getBean("pizzaDAO");
		//BeverageDAO beverageDao = (BeverageDAO) context.getBean("beverageDAO");
		//MenuDAO menuDao = (MenuDAO) context.getBean("menuDAO");
		
		//List<Pizza> pizzas = (List<Pizza>) pizzaDao.getAll();
		
		PizzeriaDAO pizzeriaDao=(PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria=pizzeriaDao.getAll().get(0);
		//model.addAttribute("pizzasList", pizzas);
		//model.addAttribute("beveragesList", beverages);
		//model.addAttribute("menusList", menus);
		model.addAttribute("pizzeria", pizzeria);
		return "pizzerialiveorder";
	}
	
	@RequestMapping(value = "/pizzerialiveorderConferme", method = RequestMethod.POST)
	public @ResponseBody String confermeLiveOrder(Model model, @RequestParam ("pizzas") String json1,@RequestParam ("beverages") String json2) {
		logger.info("Live order confermed");
		System.out.println(json1);
		System.out.println(json2);
		//REDIRECT TO BOOKING
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode actualObj = mapper.readTree(json1);
			System.out.println(actualObj.get(0).get("pizza").toString());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*PizzaOrderItem pizza=new PizzaOrderItem();
		BeverageOrderItem beverage= new BeverageOrderItem();
		BookingPizzeriaTable booking= new BookingPizzeriaTable();*/
		
		String a="{ciao:io}";
		return a;
	}
	
	@RequestMapping(value = "/pizzerialiveorderPizzas", method = RequestMethod.GET)
	public @ResponseBody Pizzeria processAJAXRequest(HttpServletRequest request, Model model) {
		
		PizzeriaDAO pizzeriaDao=(PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria=pizzeriaDao.getAll().get(0);
		System.out.println("arrivato qui");
		
		ObjectMapper mapper = new ObjectMapper();
		 
		SimpleModule module = new SimpleModule();
		module.addSerializer(Pizzeria.class, new PizzeriaSerializer());
		mapper.registerModule(module);
		 
		try {
			String serialized = mapper.writeValueAsString(pizzeria);
			System.out.println(serialized);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pizzeria;
	}
}
package it.unical.pizzamanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unical.pizzamanager.models.BookingModel;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.utils.BookingUtils;
import it.unical.pizzamanager.utils.SessionUtils;
import it.unical.pizzamanager.utils.ValidatorUtils;

@Controller
public class PizzeriaLiveOrderController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaLiveOrderController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzerialiveorder", method = RequestMethod.GET)
	public String pizzeriaLiveOrder(Model model, HttpSession session) {
		logger.info("Live order requested");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));
		
		List<Pizza> uniquePizzaForName=new ArrayList<>();
		for (RelationPizzeriaPizza pizzaPrice : pizzeria.getPizzasPriceList()) {
			 Pizza pizza=pizzaPrice.getPizza();
			 boolean found=false;
			 for (Pizza pizza1 : uniquePizzaForName) {
				 if(pizza.getName().equals(pizza1.getName()))
					found=true;
			 }
			 
			 if(found==false){
				 uniquePizzaForName.add(pizza);
			 }
		}
		
		model.addAttribute("pizzeria", pizzeria);
		model.addAttribute("pizzas", uniquePizzaForName);
		
		return "pizzerialiveorder";
	}

	@RequestMapping(value = "/pizzerialiveorderConferme", method = RequestMethod.POST)
	public @ResponseBody String confermeLiveOrder(Model model, @RequestParam("booking") String jsonBooking,HttpSession session) {
		logger.info("Live order confermed");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));
		
		
		System.out.println(jsonBooking);
		String REGEX = "[^&%$#!~]*";
		if(ValidatorUtils.ValidateString(REGEX, jsonBooking)==false){
			System.out.println("QUI");
			return null;
		}
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		BookingModel book=null;
		String message="error";
		try {
			book = objectMapper.readValue(jsonBooking, BookingModel.class);
			System.out.println("LiveOrderControlled --> id booking:"+book.getId() +";  (se null è un nuovo booking)");
			System.out.println("LiveOrderControlled --> underTheNameOf:"+book.getUnderTheNameOf());
			BookingUtils.createBookingFromBookingModelAndSave(book,pizzeria,context);
			
			message="complete";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return message;
	}

	@RequestMapping(value = "/pizzerialiveorderPizzas", method = RequestMethod.GET)
	public @ResponseBody Pizzeria processAJAXRequest(HttpServletRequest request, Model model, HttpSession session) {
		logger.info("Live order Ajax request pizzeria");
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		//IL SEGUENTE CODICE COMMENTATO SERVE SOLO PER STAMPARE CIÒ CHE FA LA SERIALIZZAZIONE(non è necessario per serializzare)
		/*ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Pizzeria.class, new PizzeriaSerializer());
		mapper.registerModule(module);
		try {
			String serialized = mapper.writeValueAsString(pizzeria);
			System.out.println(serialized);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return pizzeria;
	}
}
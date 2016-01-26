package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.PizzaUtils;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final String MODEL_ATTRIBUTE_USER = "user";
	private static final String MODEL_ATTRIBUTE_PIZZERIA = "pizzeria";

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.info("Home page requested.");

		if (SessionUtils.isUser(session)) {
			populateUserModel(session, model);
			return "homeUser";
		} else if (SessionUtils.isPizzeria(session)) {
			populatePizzeriaModel(session, model);
			return "homePizzeria";
		}

		return "signup";
	}

	private void populateUserModel(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		List<Pizza> pizze = pizzaDAO.getAll();
		OrderItemDAO orderItemDAO = (OrderItemDAO) context.getBean("orderItemDAO"); 
		List<PizzaUtils> p= new ArrayList<>();
		
		List<Pizza> pizzaRated= new ArrayList<>(); 
		
		for(int i=0; i<pizze.size(); i++)
		{
			p.add(new PizzaUtils(orderItemDAO.getNumberOfOrderPizza(pizze.get(i).getName()), pizze.get(i)));
		}
		p.sort(new Comparator<PizzaUtils>() {
			public int compare(PizzaUtils p1, PizzaUtils p2) 
			{ return Integer.compare(p1.getNumberOccurrence(),p2.getNumberOccurrence());}
		});
		
		for(int i=0; i<p.size(); i++)
			pizzaRated.add(p.get(i).getPizza());
		model.addAttribute(MODEL_ATTRIBUTE_USER, user);
		model.addAttribute("radius", 5);
		model.addAttribute("numberOfFeedbacks", 5);
		model.addAttribute("top", pizzaRated);
	}

	private void populatePizzeriaModel(HttpSession session, Model model) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));
		model.addAttribute(MODEL_ATTRIBUTE_PIZZERIA, pizzeria);
	}
}
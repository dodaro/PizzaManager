package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzaController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizza", method = RequestMethod.GET)
	public String pizza(@RequestParam Integer id, HttpSession session, Model model) {
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		Pizza pizza = pizzaDAO.get(id);
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerie = pizzeriaDAO.getAll();
		RelationPizzeriaPizzaDAO relationPizzeriaPizzaDAO = (RelationPizzeriaPizzaDAO) context.getBean("relationPizzeriaPizzaDAO");
		List<RelationPizzeriaPizza> relationPizzeriaPizza;
		
		List<RelationPizzeriaPizza> result=new ArrayList<>();
		for(int i=0; i<pizzerie.size(); i++)
		{
			relationPizzeriaPizza = relationPizzeriaPizzaDAO.get(pizzerie.get(i));
			if(!relationPizzeriaPizza.isEmpty())
			{for(int j=0; j<relationPizzeriaPizza.size(); j++)
			{
				if((relationPizzeriaPizza.get(j).getPizza().getName()).equals(pizza.getName()) && relationPizzeriaPizza.get(j).getAvailable())
					{
					result.add(relationPizzeriaPizza.get(j));
					}
			}}
				
		}
		model.addAttribute("searchedPizza", pizza);
		model.addAttribute("pizzeriaResult", result);
		setUserAttribute(session, model);
		return "pizza";
	}

	/*
	 * private void setUserAttribute(HttpSession session, Model model) { UserDAO
	 * userDAO = (UserDAO) context.getBean("userDAO"); User user =
	 * userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
	 * model.addAttribute("user", user); }
	 */
	
	private void setUserAttribute(HttpSession session, Model model) {
		if (!SessionUtils.isUser(session)) {
			String login = "Login";
			model.addAttribute("typeSession", login);
		} else {
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
			String account = "Account";
			model.addAttribute("typeSession", account);
			model.addAttribute("user", user);
		}
	}
}

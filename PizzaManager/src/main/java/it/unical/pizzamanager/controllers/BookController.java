package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class BookController {

	@Autowired
	private WebApplicationContext context;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(HttpSession session,Model model) {
		
		Pizzeria pizzeria = (Pizzeria) session.getAttribute("pizzeriaResult");
		RelationPizzeriaPizzaDAO relationPizzeriaPizzaDAO = (RelationPizzeriaPizzaDAO) context.getBean("relationPizzeriaPizzaDAO"); 
		List<RelationPizzeriaPizza> relationPizzeriaPizza = relationPizzeriaPizzaDAO.get(pizzeria);
		model.addAttribute("menuResult", relationPizzeriaPizza);
		model.addAttribute("pizzeriaResult", pizzeria);
		setUserAttribute(session, model);
		
		return "book";
	}
	
	private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}
}

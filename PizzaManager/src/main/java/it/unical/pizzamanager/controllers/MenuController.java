package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class MenuController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(@RequestParam Integer id, HttpSession session, Model model) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(id);
		RelationPizzeriaPizzaDAO relationPizzeriaPizzaDAO = (RelationPizzeriaPizzaDAO) context
				.getBean("relationPizzeriaPizzaDAO");
		List<RelationPizzeriaPizza> relationPizzeriaPizza = relationPizzeriaPizzaDAO.get(pizzeria);
		model.addAttribute("menuResult", relationPizzeriaPizza);
		model.addAttribute("pizzeriaResult", pizzeria);
		setUserAttribute(session, model);
		return "menu";
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

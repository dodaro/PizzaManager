package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.Pizza.PizzaSize;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaPizzaManagerController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriaPizzaManager", method = RequestMethod.GET)
	public String pizzeriaPizzaManager(Model model, HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			return "homeLogInError";
		} else {
			PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
			List<Pizza> pizzas = pizzaDAO.getAll();

			model.addAttribute("pizzas", pizzas);
			model.addAttribute("sizes", PizzaSize.values());

			return "pizzeriaPizzaManager";
		}
	}

	@RequestMapping(value = "/pizzeria/pizzas", method = RequestMethod.GET)
	public @ResponseBody List<RelationPizzeriaPizza> getPizzas(HttpSession session) {
		/*
		 * If the author of the request is not logged in as a pizzeria, negate
		 * the information.
		 */
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		return pizzeria.getPizzasPriceList();
	}
}
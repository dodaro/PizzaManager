package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.PizzeriaPizzaForm;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.entities.Pizza;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.entities.Pizza.PizzaSize;
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

	@RequestMapping(value = "/pizzeria/pizzasList", method = RequestMethod.GET)
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

		return pizzeria.getAvailablePizzasPriceList();
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeria/pizza", method = RequestMethod.POST)
	public String handleRequest(HttpSession session, @ModelAttribute PizzeriaPizzaForm form) {
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		Pizza pizza = pizzaDAO.get(form.getPizzaId());

		switch (form.getAction()) {
		case "add":
			return addPizza(pizzeria, pizza, form);
		case "update":
			return updatePizza(pizzeria, pizza, form);
		case "delete":
			return deletePizza(pizzeria, pizza, form);
		}

		return buildErrorResponse();
	}

	private String addPizza(Pizzeria pizzeria, Pizza pizza, PizzeriaPizzaForm form) {
		RelationPizzeriaPizza pizzeriaPizza = new RelationPizzeriaPizza(pizzeria, pizza,
				form.getPrice(), form.getSize(), form.getPreparationTimeInSeconds(),
				form.getGlutenFree());

		// FIXME - Validate data: user can't add an entry that already exists
		// (same data).

		RelationPizzeriaPizzaDAO dao = (RelationPizzeriaPizzaDAO) context
				.getBean("relationPizzeriaPizzaDAO");

		dao.create(pizzeriaPizza);

		/*
		 * Now the pizzeriaPizza object contains the id of the newly created
		 * instance.
		 */
		return buildOkResponse(pizzeriaPizza);
	}

	private String updatePizza(Pizzeria pizzeria, Pizza pizza, PizzeriaPizzaForm form) {
		List<RelationPizzeriaPizza> pizzeriaPizzas = pizzeria.getPizzasPriceList();

		/*
		 * Only update the pizza if the pizzeria has already a
		 * RelationPizzeriaPizza with the same id.
		 */
		for (RelationPizzeriaPizza pizzeriaPizza : pizzeriaPizzas) {
			if (pizzeriaPizza.getId() == form.getId()) {
				pizzeriaPizza.setPizza(pizza);
				pizzeriaPizza.setPizzaSize(form.getSize());
				pizzeriaPizza.setPreparationTime(form.getPreparationTimeInSeconds());
				pizzeriaPizza.setGlutenFree(form.getGlutenFree());
				pizzeriaPizza.setPrice(form.getPrice());

				RelationPizzeriaPizzaDAO dao = (RelationPizzeriaPizzaDAO) context
						.getBean("relationPizzeriaPizzaDAO");
				dao.update(pizzeriaPizza);

				return buildOkResponse(pizzeriaPizza);
			}
		}

		return buildErrorResponse();
	}

	private String deletePizza(Pizzeria pizzeria, Pizza pizza, PizzeriaPizzaForm form) {
		List<RelationPizzeriaPizza> pizzeriaPizzas = pizzeria.getPizzasPriceList();

		/*
		 * Only update the pizza if the pizzeria has already a
		 * RelationPizzeriaPizza with the same id.
		 */
		for (RelationPizzeriaPizza pizzeriaPizza : pizzeriaPizzas) {
			if (pizzeriaPizza.getId() == form.getId()) {

				/*
				 * FIXME - Prevent the elimination of RelationPizzeriaPizzas
				 * which belong to an OrderItem which has not yet been
				 * collected.
				 */

				RelationPizzeriaPizzaDAO dao = (RelationPizzeriaPizzaDAO) context
						.getBean("relationPizzeriaPizzaDAO");
				pizzeriaPizza.setAvailable(false);
				dao.update(pizzeriaPizza);
				return buildOkResponse(pizzeriaPizza);
			}
		}

		return buildErrorResponse();

	}

	private String buildOkResponse(RelationPizzeriaPizza pizzeriaPizza) {
		return "{\"success\" : true, \"id\" : " + pizzeriaPizza.getId() + ", \"pizzaId\" : "
				+ pizzeriaPizza.getPizza().getId() + ", \"pizzaName\" : \""
				+ pizzeriaPizza.getPizza().getName() + "\", \"size\" : \""
				+ pizzeriaPizza.getPizzaSize() + "\", \"preparationTime\" : \""
				+ pizzeriaPizza.getPreparationTimeString() + "\", \"glutenFree\" : "
				+ pizzeriaPizza.getGlutenFree() + ", \"price\" : " + pizzeriaPizza.getPrice() + "}";
	}

	private String buildErrorResponse() {
		return "{\"success\" : false}";
	}
}
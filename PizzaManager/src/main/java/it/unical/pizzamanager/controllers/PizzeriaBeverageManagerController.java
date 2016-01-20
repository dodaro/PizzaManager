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

import it.unical.pizzamanager.forms.PizzeriaBeverageForm;
import it.unical.pizzamanager.persistence.dao.BeverageDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaBeverageDAO;
import it.unical.pizzamanager.persistence.dto.Beverage;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaBeverage;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaBeverageManagerController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriaBeverageManager", method = RequestMethod.GET)
	public String pizzeriaTable(Model model, HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			return "homeLogInError";
		} else {
			BeverageDAO beverageDAO = (BeverageDAO) context.getBean("beverageDAO");
			List<Beverage> beverages = beverageDAO.getAll();

			model.addAttribute("beverages", beverages);

			return "pizzeriaBeverageManager";
		}
	}

	/* Controller to add, update or delete beverages. */
	@ResponseBody
	@RequestMapping(value = "/pizzeria/beverages", method = RequestMethod.POST)
	public String handleRequest(HttpSession session, @ModelAttribute PizzeriaBeverageForm form) {
		/*
		 * If the author of the request is not logged in as a pizzeria, the request is ignored.
		 */
		if (!SessionUtils.isPizzeria(session)) {
			return buildErrorResponse();
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		switch (form.getAction()) {
		case "add":
			return addBeverage(pizzeria, form);
		case "update":
			return updateBeverage(pizzeria, form);
		case "delete":
			return deleteBeverage(pizzeria, form);
		}

		return buildErrorResponse();

	}

	@RequestMapping(value = "/pizzeria/beveragesList", method = RequestMethod.GET)
	public @ResponseBody List<RelationPizzeriaBeverage> getBeveragesList(HttpSession session) {
		/* If the author of the request is not logged in as a pizzeria, negate the information. */
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		List<RelationPizzeriaBeverage> beverages = pizzeria.getAvailableBeveragesPriceList();

		return beverages;
	}

	private String addBeverage(Pizzeria pizzeria, PizzeriaBeverageForm form) {
		BeverageDAO beverageDAO = (BeverageDAO) context.getBean("beverageDAO");
		Beverage beverage = beverageDAO.get(form.getBeverageId());

		RelationPizzeriaBeverage newBeverage = new RelationPizzeriaBeverage(pizzeria, beverage,
				form.getPrice());

		// TODO - Validate the input

		RelationPizzeriaBeverageDAO dao = (RelationPizzeriaBeverageDAO) context
				.getBean("relationPizzeriaBeverageDAO");
		dao.create(newBeverage);

		/* Now the newTable object contains the id of the newly created instance. */
		return buildOkResponse(newBeverage);
	}

	private String updateBeverage(Pizzeria pizzeria, PizzeriaBeverageForm form) {
		RelationPizzeriaBeverageDAO dao = (RelationPizzeriaBeverageDAO) context
				.getBean("relationPizzeriaBeverageDAO");
		RelationPizzeriaBeverage pizzeriaBeverage = dao.get(form.getId());

		BeverageDAO beverageDAO = (BeverageDAO) context.getBean("beverageDAO");
		Beverage beverage = beverageDAO.get(form.getBeverageId());

		pizzeriaBeverage.setBeverage(beverage);
		pizzeriaBeverage.setPrice(form.getPrice());

		// TODO - Form validation.

		dao.update(pizzeriaBeverage);

		return buildOkResponse(pizzeriaBeverage);
	}

	private String deleteBeverage(Pizzeria pizzeria, PizzeriaBeverageForm form) {
		RelationPizzeriaBeverageDAO dao = (RelationPizzeriaBeverageDAO) context
				.getBean("relationPizzeriaBeverageDAO");
		RelationPizzeriaBeverage pizzeriaBeverage = dao.get(form.getId());

		/* If the beverage belongs to an order, it cannot be deleted. */
		if (pizzeriaBeverage.getOrderItems().size() > 0) {
			return buildErrorResponse();
		} else {
			pizzeriaBeverage.setAvailable(false);
			dao.update(pizzeriaBeverage);
			return buildOkResponse(pizzeriaBeverage);
		}
	}

	private String buildOkResponse(RelationPizzeriaBeverage relation) {
		return "{\"success\" : true, \"id\" : " + relation.getId() + ", \"beverageId\" : "
				+ relation.getBeverage().getId() + ", \"name\" : \""
				+ relation.getBeverage().getName() + "\", \"brand\" : \""
				+ relation.getBeverage().getBrand() + "\", \"container\" : \""
				+ relation.getBeverage().getContainer() + "\", \"size\" : \""
				+ relation.getBeverage().getSize() + "\", \"type\" : \""
				+ relation.getBeverage().getType() + "\", \"price\" : " + relation.getPrice() + "}";

	}

	private String buildErrorResponse() {
		return "{\"success\" : false}";
	}
}

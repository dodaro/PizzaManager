package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.PizzeriaTableForm;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaTableDAO;
import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.PizzeriaTable;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaTableManagerController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriaTableManager", method = RequestMethod.GET)
	public String pizzeriaTable(HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			return "homeLogInError";
		} else {
			return "pizzeriaTableManager";
		}
	}

	/* Controller to add, update or delete tables. */
	@ResponseBody
	@RequestMapping(value = "/pizzeria/tables", method = RequestMethod.POST)
	public String handleRequest(HttpSession session, @ModelAttribute PizzeriaTableForm form) {
		/*
		 * If the author of the request is not logged in as a pizzeria, the request is ignored.
		 */
		if (!SessionUtils.isPizzeria(session)) {
			return buildErrorResponse();
		}

		/*
		 * Action will be performed only if the server received a valid table, which means that
		 * maxSeats are greater than or equal to minSeats.
		 */
		if (form.getMaxSeats() >= form.getMinSeats()) {
			PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
			Pizzeria pizzeria = pizzeriaDAO
					.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

			switch (form.getAction()) {
			case "add":
				return addTable(pizzeria, form);
			case "update":
				return updateTable(pizzeria, form);
			case "delete":
				return deleteTable(pizzeria, form);
			}
		}

		return buildErrorResponse();
	}

	@RequestMapping(value = "/pizzeria/tablesList", method = RequestMethod.GET)
	public @ResponseBody List<PizzeriaTable> getTablesList(HttpSession session) {
		/* If the author of the request is not logged in as a pizzeria, negate the information. */
		if (!SessionUtils.isPizzeria(session)) {
			return null;
		}

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(SessionUtils.getPizzeriaIdFromSessionOrNull(session));

		List<PizzeriaTable> tables = pizzeria.getTables();

		return tables;
	}

	private String addTable(Pizzeria pizzeria, PizzeriaTableForm form) {
		List<PizzeriaTable> tables = pizzeria.getTables();
		PizzeriaTable newTable = new PizzeriaTable(form.getNumber(), form.getMinSeats(),
				form.getMaxSeats(), pizzeria);

		/* Refuse to add the table if the pizzeria has already a table with the same number. */
		for (PizzeriaTable table : tables) {
			if (table.getNumber() == form.getNumber()) {
				return buildErrorResponse();
			}
		}

		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");
		pizzeriaTableDAO.create(newTable);

		/* Now the newTable object contains the id of the newly created instance. */
		return buildOkResponse(newTable);
	}

	private String updateTable(Pizzeria pizzeria, PizzeriaTableForm form) {
		List<PizzeriaTable> tables = pizzeria.getTables();

		/* Only update the table if the pizzeria has already a table with the same id. */
		for (PizzeriaTable table : tables) {
			if (table.getId() == form.getId()) {
				return buildOkResponse(doUpdate(table, form));
			}
		}

		return buildErrorResponse();
	}

	private String deleteTable(Pizzeria pizzeria, PizzeriaTableForm form) {
		List<PizzeriaTable> tables = pizzeria.getTables();

		/* Only delete the table if the pizzeria has already a table with the same id. */
		for (PizzeriaTable table : tables) {
			if (table.getId() == form.getId()) {
				PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context
						.getBean("pizzeriaTableDAO");
				pizzeriaTableDAO.delete(table);
				return buildOkResponse(table);
			}
		}

		return buildErrorResponse();
	}

	private PizzeriaTable doUpdate(PizzeriaTable table, PizzeriaTableForm form) {
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");
		table.setNumber(form.getNumber());
		table.setMinSeats(form.getMinSeats());
		table.setMaxSeats(form.getMaxSeats());
		pizzeriaTableDAO.update(table);
		return table;
	}

	private String buildOkResponse(PizzeriaTable table) {
		return "{\"success\" : true, \"id\" : " + table.getId() + ", \"number\" : "
				+ table.getNumber() + ", \"minSeats\" : " + table.getMinSeats()
				+ ", \"maxSeats\" : " + table.getMaxSeats() + "}";
	}

	private String buildErrorResponse() {
		return "{\"success\" : false}";
	}
}

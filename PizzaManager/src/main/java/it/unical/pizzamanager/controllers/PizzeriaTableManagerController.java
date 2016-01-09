package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaTableManagerController {

	private static final Logger logger = LoggerFactory
			.getLogger(PizzeriaTableManagerController.class);

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
	public String performAction(HttpSession session, @ModelAttribute PizzeriaTableForm form) {
		if (!SessionUtils.isPizzeria(session)) {
			return buildJsonResponse(false, null);
		}

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

		return buildJsonResponse(false, form);
	}

	@RequestMapping(value = "/pizzeria/tablesList", method = RequestMethod.GET)
	public @ResponseBody List<PizzeriaTable> getTablesList(HttpSession session) {
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

		/* Refuse to add the table if the pizzeria has already a table with the same number. */
		for (PizzeriaTable table : tables) {
			if (table.getNumber() == form.getNumber()) {
				return buildJsonResponse(false, form);
			}
		}

		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");
		PizzeriaTable table = new PizzeriaTable(form.getNumber(), form.getMinSeats(),
				form.getMaxSeats(), pizzeria);
		pizzeriaTableDAO.create(table);

		/* We need to provide the id of the table that has just been added to the view. */

		List<PizzeriaTable> updatedTables = pizzeriaTableDAO.getTablesOfPizzeria(pizzeria);

		for (PizzeriaTable updatedTable : updatedTables) {
			if (updatedTable.getNumber() == form.getNumber()) {
				form.setId(updatedTable.getId());
			}
		}

		return buildJsonResponse(true, form);
	}

	private String updateTable(Pizzeria pizzeria, PizzeriaTableForm form) {
		List<PizzeriaTable> tables = pizzeria.getTables();

		for (PizzeriaTable table : tables) {
			if (table.getId() == form.getId()) {
				doUpdate(table, form);
				return buildJsonResponse(true, form);
			}
		}

		return buildJsonResponse(false, form);
	}

	private String deleteTable(Pizzeria pizzeria, PizzeriaTableForm form) {
		List<PizzeriaTable> tables = pizzeria.getTables();

		/* Only delete the table if the pizzeria has already a table with the same number. */
		for (PizzeriaTable table : tables) {
			if (table.getNumber() == form.getNumber()) {
				PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context
						.getBean("pizzeriaTableDAO");
				pizzeriaTableDAO.delete(table);
				return buildJsonResponse(true, form);
			}
		}

		return buildJsonResponse(false, form);
	}

	private void doUpdate(PizzeriaTable table, PizzeriaTableForm form) {
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");
		table.setNumber(form.getNumber());
		table.setMinSeats(form.getMinSeats());
		table.setMaxSeats(form.getMaxSeats());
		pizzeriaTableDAO.update(table);
	}

	private String buildJsonResponse(boolean success, PizzeriaTableForm form) {
		if (!success) {
			return "{\"success\" : false}";
		}

		return "{\"success\" : " + success + ", \"id\" : " + form.getId() + ", \"number\" : "
				+ form.getNumber() + ", \"minSeats\" : " + form.getMinSeats() + ", \"maxSeats\" : "
				+ form.getMaxSeats() + "}";
	}
}

package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.serializers.PizzeriaTableSerializer;
import it.unical.pizzamanager.utils.JsonUtils;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaTableController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaTableController.class);

	@RequestMapping(value = "/pizzeriaTable", method = RequestMethod.GET)
	public String pizzeriaTable(HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			return "redirect:/";
		}

		return "pizzeriaTable";
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeria/tablesList", method = RequestMethod.GET)
	public String getTablesList(HttpSession session) {
		if (!SessionUtils.isPizzeria(session)) {
			// TODO
			return "";
		}

		Pizzeria pizzeria = SessionUtils.getPizzeriaFromSessionOrNull(session);

		List<PizzeriaTable> tables = pizzeria.getTables();

		return JsonUtils.serializeListAsArray(tables, PizzeriaTable.class,
				new PizzeriaTableSerializer());
	}
}

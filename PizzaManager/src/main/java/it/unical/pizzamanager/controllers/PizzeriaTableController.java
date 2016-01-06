package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class PizzeriaTableController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaTableController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriaTable", method = RequestMethod.GET)
	public String pizzeriaTable(HttpSession session) {
		if (!LogInController.isPizzeria(session)) {
			return "redirect:/";
		}

		return "pizzeriaTable";
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeria/tablesList", method = RequestMethod.GET)
	public String getTablesList(HttpSession session) {
		if (!LogInController.isPizzeria(session)) {
			return "redirect:/";
		}
		
		logger.info("Tables list requested.");
		return "{ \"data\" : [{\"number\" : \"1\", \"minSits\" : \"2\"}]}";
	}

}

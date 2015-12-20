package it.unical.pizzamanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

@Controller
public class PizzeriaController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeria", method = RequestMethod.GET)
	public String home(Model model, @RequestParam Integer id) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(id);
		logger.info("Pizzeria page requested for pizzeria " + pizzeria.getName() + ".");

		model.addAttribute("pizzeria", pizzeria);

		return "pizzeria";
	}

}

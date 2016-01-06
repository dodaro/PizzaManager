package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final String MODEL_ATTRIBUTE_USER = "user";
	private static final String MODEL_ATTRIBUTE_PIZZERIA = "pizzeria";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.info("Home page requested.");

		if (SessionUtils.isUser(session)) {
			populateUserModel(session, model);
			return "homeUser";
		} else if (SessionUtils.isPizzeria(session)) {
			populatePizzeriaModel(session, model);
			return "homePizzeria";
		}

		return "index";
	}

	private void populateUserModel(HttpSession session, Model model) {
		model.addAttribute(MODEL_ATTRIBUTE_USER, SessionUtils.getUserFromSessionOrNull(session));
	}

	private void populatePizzeriaModel(HttpSession session, Model model) {
		model.addAttribute(MODEL_ATTRIBUTE_PIZZERIA,
				SessionUtils.getPizzeriaFromSessionOrNull(session));
	}
}
package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.info("Home page requested.");

		if (LogInController.isUser(session)) {
			populateUserModel(session, model);
			return "homeUser";
		} else if (LogInController.isPizzeria(session)) {
			populatePizzeriaModel(session, model);
			return "homePizzeria";
		}

		return "index";
	}

	private void populateUserModel(HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute(LogInController.SESSION_ATTRIBUTE_USER));
	}

	private void populatePizzeriaModel(HttpSession session, Model model) {
		model.addAttribute("pizzeria",
				session.getAttribute(LogInController.SESSION_ATTRIBUTE_PIZZERIA));
	}
}
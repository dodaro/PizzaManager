package it.unical.pizzamanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unical.pizzamanager.persistence.DAOFactory;
import it.unical.pizzamanager.persistence.User;
import it.unical.pizzamanager.persistence.UserDAO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Home page requested. Loading list of users.");

		UserDAO dao = DAOFactory.get().getUserDAO();
		List<User> users = dao.get();

		model.addAttribute("users", users);

		return "home";
	}

}

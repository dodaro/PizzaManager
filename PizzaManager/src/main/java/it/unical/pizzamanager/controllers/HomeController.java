package it.unical.pizzamanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.LogInForm;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.info("Home page requested. Loading list of users.");

		setNavbar(model, session);

		if (!LogInController.isLoggedIn(session)) {
			return "index";
		}

		UserDAO dao = (UserDAO) context.getBean("userDAO");
		List<User> users = dao.getAll();

		model.addAttribute("users", users);


		return "home";
	}

	/*
	 * È opportuno inserire questo controllo qui piuttosto che nella view per rispettare il pattern
	 * MVC? È questo il modo migliore per implementarlo?
	 */
	private void setNavbar(Model model, HttpSession session) {
		if (LogInController.isLoggedIn(session)) {
			model.addAttribute("navbar", "navbarUser");
		} else {
			model.addAttribute("navbar", "navbarLogin");
			model.addAttribute("logInForm", new LogInForm());
		}
	}
}
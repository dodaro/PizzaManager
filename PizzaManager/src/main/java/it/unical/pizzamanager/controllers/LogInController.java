package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.LogInForm;
import it.unical.pizzamanager.persistence.dao.AccountDAO;
import it.unical.pizzamanager.persistence.dto.Account;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class LogInController {

	public static final String SESSION_ATTRIBUTE_USER = "user";
	public static final String SESSION_ATTRIBUTE_PIZZERIA = "pizzeria";

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute("logInForm") LogInForm form) {

		AccountDAO accountDAO = (AccountDAO) context.getBean("accountDAO");
		Account account = accountDAO.get(form.getEmail());

		if (account != null && account.getPassword().equals(form.getPassword())) {
			/*
			 * Check if the account is a User or a Pizzeria.
			 */
			if (account instanceof User) {
				User user = (User) account;
				session.setAttribute(SESSION_ATTRIBUTE_USER, user);
				logger.info("The account is a User");
			} else if (account instanceof Pizzeria) {
				Pizzeria pizzeria = (Pizzeria) account;
				session.setAttribute(SESSION_ATTRIBUTE_PIZZERIA, pizzeria);
				logger.info("The account is a Pizzeria");
			}
		} else {
			logger.info("Login failed");
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	public static boolean isLoggedIn(HttpSession session) {
		return isUser(session) || isPizzeria(session);
	}

	public static boolean isPizzeria(HttpSession session) {
		return session.getAttribute(SESSION_ATTRIBUTE_PIZZERIA) != null;
	}

	public static boolean isUser(HttpSession session) {
		return session.getAttribute(SESSION_ATTRIBUTE_USER) != null;
	}
}

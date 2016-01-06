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
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Account;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class LogInController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute("logInForm") LogInForm form) {

		AccountDAO accountDAO = (AccountDAO) context.getBean("accountDAO");
		Account account = accountDAO.get(form.getEmail());

		if (account != null && account.getPassword().equals(form.getPassword())) {
			/*
			 * Check if the account is a User or a Pizzeria. We also need to retrieve the full User
			 * or the full Pizzeria before storing it into the session.
			 */
			if (account instanceof User) {
				UserDAO userDAO = (UserDAO) context.getBean("userDAO");
				User user = userDAO.get(account.getId());
				SessionUtils.storeUserInSession(session, user);
			} else if (account instanceof Pizzeria) {
				PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
				Pizzeria pizzeria = pizzeriaDAO.get(account.getId());
				SessionUtils.storePizzeriaInSession(session, pizzeria);
			}
		} else {
			logger.info("Login failed");
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		SessionUtils.clearSession(session);
		return "redirect:/";
	}
}
package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.LogInForm;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class LogInController {

	private static final String SESSION_ATTRIBUTE_USER = "user";

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session,
			@ModelAttribute("logInForm") LogInForm form) {

		UserDAO dao = (UserDAO) context.getBean("userDAO");
		User user = dao.get(form.getEmail());

		if (user != null && user.getPassword().equals(form.getPassword())) {
			session.setAttribute(SESSION_ATTRIBUTE_USER, user);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	public static boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(SESSION_ATTRIBUTE_USER) != null;
	}
}

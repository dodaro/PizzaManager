package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.UserDisplay;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class SettingsController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(@ModelAttribute("userForm") UserDisplay user, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

		UserDisplay settingUser = new UserDisplay(loggedUser);
		model.addAttribute("userDisplay", settingUser);

		return "settings";
	}

	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public String modifySettings(@ModelAttribute("userForm") UserDisplay user, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		System.out.println(loggedUser.getEmail());
		System.out.println(user.getEmail());

		return "settings";
	}
}

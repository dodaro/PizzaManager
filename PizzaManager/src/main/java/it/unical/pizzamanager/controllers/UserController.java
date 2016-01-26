package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model, HttpSession session) {

		if (SessionUtils.isUser(session)) {
			setUserAttribute(session, model);
		} else {
			return "index";
		}
		return "user";
	}


	private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}
}

package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.SearchForm;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class ResultPageController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/resultpage", method = RequestMethod.GET)
	public String resultpage(Model model, HttpSession session) {
		
		setUserAttribute(session, model);
		return "resultpage";
	}
	
	/*private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}*/
	
	private void setUserAttribute(HttpSession session, Model model) {
		if(!SessionUtils.isUser(session))
		{
			String login = "Login";
			model.addAttribute("typeSession", login);
		}
		else
			{
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
			String account = "Account";
			model.addAttribute("typeSession", account);
			model.addAttribute("user", user);}
	}
	
}
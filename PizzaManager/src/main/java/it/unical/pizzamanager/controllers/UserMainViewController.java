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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/usermainview", method = RequestMethod.GET)
	public String usermainview(@RequestParam Integer id, HttpSession session,Model model) {
		
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(id);
		List <Feedback> feedbacks = user.getFeedbacks();
		setUserAttribute(session, model);
		model.addAttribute("feedbacksuser", feedbacks);
		model.addAttribute("searchedUser", user);
		return "usermainview";
	}
	
	
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
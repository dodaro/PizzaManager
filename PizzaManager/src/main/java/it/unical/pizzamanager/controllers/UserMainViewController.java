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

import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	private static Integer userid;
	
	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/usermainview", method = RequestMethod.GET)
	public String usermainview(HttpSession session,Model model) {
		
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(userid);
		List <Feedback> feedbacks = user.getFeedbacks();
		setUserAttribute(session, model);
		model.addAttribute("feedbacksuser", feedbacks);
		model.addAttribute("searchedUser", user);
		return "usermainview";
	}
	
	@RequestMapping(value = "/usermainview", method = RequestMethod.POST)
	public String usermainview(@RequestParam Integer id, HttpSession session,Model model) {
		
		userid=id;
		setUserAttribute(session, model);
		return "usermainview";
	}
	private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}

}
package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class BookingListController {

	
	
	@Autowired
	WebApplicationContext context;
	
	@RequestMapping(value="/bookingList",method=RequestMethod.POST)
	public String bookingList(Model model,HttpSession session){
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		
		model.addAttribute("user", user);
		
		
		return "bookingList";
	}
}

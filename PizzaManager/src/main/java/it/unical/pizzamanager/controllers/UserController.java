package it.unical.pizzamanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class UserController {

	
	@Autowired
	private WebApplicationContext context;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model){
		
		User user=new User("mail1", "1111","UserName");
		
		
		model.addAttribute("user",user);
		return "user";
	}
}

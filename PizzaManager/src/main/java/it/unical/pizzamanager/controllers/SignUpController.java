package it.unical.pizzamanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.SignUpForm;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class SignUpController {

	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@ModelAttribute("signUpForm") SignUpForm form) {
		logger.info("Adding a new user to the database with email: " + form.getEmail()
				+ ", password: " + form.getPassword());

		User user = new User(form.getEmail(), form.getPassword(),form.getEmail());
		UserDAO dao = (UserDAO) context.getBean("userDAO");

		dao.create(user);

		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = "/signup/emailTaken", method = RequestMethod.GET)
	public String isEmailTaken(@RequestParam String email) {
		logger.info("Request for email " + email);

		boolean taken;

		/* TODO - Input validation is strongly needed here. */

		/* TODO - A database query is needed here. */
		taken = email.equals("a@a.a");

		/*
		 * Return a JSON objects with fields "email" and "taken" (true/false).
		 */
		return "{\"email\" : \"" + email + "\", \"taken\" : " + taken + "}";
	}
}

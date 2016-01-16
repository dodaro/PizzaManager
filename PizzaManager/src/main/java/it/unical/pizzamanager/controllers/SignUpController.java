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

import it.unical.pizzamanager.forms.PizzeriaSignUpForm;
import it.unical.pizzamanager.forms.UserSignUpForm;
import it.unical.pizzamanager.persistence.dao.AccountDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Account;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

@Controller
public class SignUpController {

	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("userSignUpForm", new UserSignUpForm());
		model.addAttribute("pizzeriaSignUpForm", new PizzeriaSignUpForm());

		return "signup";
	}

	@RequestMapping(value = "/userSignup", method = RequestMethod.POST)
	public String userSignUp(@ModelAttribute("userSignUpForm") UserSignUpForm form) {
		logger.info("Adding a new user to the database with email: " + form.getEmail()
				+ ", password: " + form.getPassword());

		User user = new User(form.getEmail(), form.getPassword());
		UserDAO dao = (UserDAO) context.getBean("userDAO");

		dao.create(user);

		return "redirect:/";
	}

	@RequestMapping(value = "/pizzeriaSignup", method = RequestMethod.POST)
	public String pizzeriaSignUp(@ModelAttribute("pizzeriaSignUpForm") PizzeriaSignUpForm form) {
		logger.info("Adding a new pizzeria to the database with email: " + form.getEmail()
				+ ", password: " + form.getPassword());

		Pizzeria pizzeria = new Pizzeria(form.getEmail(), form.getPassword());
		PizzeriaDAO dao = (PizzeriaDAO) context.getBean("pizzeriaDAO");

		dao.create(pizzeria);

		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping(value = "/signup/emailTaken", method = RequestMethod.GET)

	public String isEmailTaken(@RequestParam String email) {
		logger.info("Request for email " + email);

		AccountDAO accountDAO = (AccountDAO) context.getBean("accountDAO");
		boolean taken;

		/* TODO - Input validation is strongly needed here. */

		Account account = accountDAO.get(email);
		taken = account != null && account.getEmail().equals(email);

		/*
		 * Return a JSON objects with fields "email" and "taken" (true/false).
		 */
		return "{\"email\" : \"" + email + "\", \"taken\" : " + taken + "}";
	}

}
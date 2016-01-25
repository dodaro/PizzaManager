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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.PizzeriaSignUpForm;
import it.unical.pizzamanager.forms.UserSignUpForm;
import it.unical.pizzamanager.persistence.dao.AccountDAO;
import it.unical.pizzamanager.persistence.dao.AddressDAO;
import it.unical.pizzamanager.persistence.dao.CartDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Account;
import it.unical.pizzamanager.persistence.dto.Address;
import it.unical.pizzamanager.persistence.dto.Cart;
import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;
import it.unical.pizzamanager.utils.ValidatorUtils;

@Controller
public class SignUpController {

	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	private static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
	private static final String USER_NAME_REGEX = "^[A-Za-z]+$";
	private static final String USER_USERNAME_REGEX = "^[A-Za-z0-9_-]{4,}$";

	private static final String PIZZERIA_NAME_REGEX = "^[A-Za-z0-9 -]+$";
	private static final String PIZZERIA_PHONE_REGEX = "^[0-9]+([- ][0-9]+)*$";
	private static final String PIZZERIA_ADDRESS_REGEX = "^[A-Za-z0-9 ]+$";

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("userSignUpForm", new UserSignUpForm());
		model.addAttribute("pizzeriaSignUpForm", new PizzeriaSignUpForm());

		return "signup";
	}

	@RequestMapping(value = "/userSignup", method = RequestMethod.POST)
	public String userSignUp(Model model, HttpSession session,
			@ModelAttribute("userSignUpForm") UserSignUpForm form) {
		logger.info("Adding a new user to the database");

		if (validateUser(form)) {
			User user = new User(form.getEmail(), form.getPassword());
			user.setName(form.getUsername());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());

			UserDAO dao = (UserDAO) context.getBean("userDAO");
			dao.create(user);

			CartDAO cartDAO = (CartDAO) context.getBean("cartDAO");
			cartDAO.create(new Cart(user));

			SessionUtils.storeUserIdInSession(session, user);

			return "redirect:/";
		} else {
			model.addAttribute("signUpError", true);
			return "signup";
		}
	}

	@RequestMapping(value = "/pizzeriaSignup", method = RequestMethod.POST)
	public String pizzeriaSignUp(Model model, HttpSession session,
			@ModelAttribute("pizzeriaSignUpForm") PizzeriaSignUpForm form) {
		logger.info("Adding a new pizzeria to the database.");

		if (validatePizzeria(form)) {
			Address address = new Address(form.getStreet(), form.getNumber(), form.getCity());
			Location location = new Location(form.getLatitude(), form.getLongitude());

			Pizzeria pizzeria = new Pizzeria(form.getEmail(), form.getPassword());
			pizzeria.setName(form.getName());
			pizzeria.setPhoneNumber(form.getPhoneNumber());

			AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
			addressDAO.create(address);

			pizzeria.setAddress(address);
			pizzeria.setLocation(location);

			PizzeriaDAO dao = (PizzeriaDAO) context.getBean("pizzeriaDAO");

			dao.create(pizzeria);

			SessionUtils.storePizzeriaIdInSession(session, pizzeria);

			return "redirect:/";
		} else {
			model.addAttribute("signUpError", true);
			return "signup";
		}
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

	@ResponseBody
	@RequestMapping(value = "/signup/usernameTaken", method = RequestMethod.GET)
	public String isUsernameTaken(@RequestParam String username) {
		logger.info("Request for username " + username);

		boolean taken;
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		User user = userDAO.getByUsername(username);
		taken = user != null && user.getName().equals(username);

		return "{\"username\" : \"" + username + "\", \"taken\" : " + taken + "}";
	}

	private Boolean validateUser(UserSignUpForm form) {
		return ValidatorUtils.ValidateString(EMAIL_REGEX, form.getEmail())
				&& ValidatorUtils.ValidateString(USER_USERNAME_REGEX, form.getUsername())
				&& ValidatorUtils.ValidateString(USER_NAME_REGEX, form.getFirstName())
				&& ValidatorUtils.ValidateString(USER_NAME_REGEX, form.getLastName());
	}

	private Boolean validatePizzeria(PizzeriaSignUpForm form) {
		return ValidatorUtils.ValidateString(EMAIL_REGEX, form.getEmail())
				&& ValidatorUtils.ValidateString(PIZZERIA_NAME_REGEX, form.getName())
				&& ValidatorUtils.ValidateString(PIZZERIA_ADDRESS_REGEX, form.getStreet())
				&& ValidatorUtils.ValidateString(PIZZERIA_ADDRESS_REGEX, form.getCity())
				&& ValidatorUtils.ValidateString(PIZZERIA_PHONE_REGEX, form.getPhoneNumber());
	}
}
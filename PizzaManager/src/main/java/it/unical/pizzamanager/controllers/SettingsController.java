package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.model.AddressDisplay;
import it.unical.pizzamanager.model.EmailSetting;
import it.unical.pizzamanager.model.PasswordSetting;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Address;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class SettingsController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model, HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

		Address userAddress = loggedUser.getAddress();
		AddressDisplay address = new AddressDisplay();
		address.setCity(userAddress.getCity());
		address.setNumber(userAddress.getNumber());
		address.setStreet(userAddress.getStreet());

		model.addAttribute("user", loggedUser);
		model.addAttribute("address", address);
		return "settings";
	}

	@RequestMapping(value = "/settings/email", method = RequestMethod.POST)
	public String modifySettings(@RequestParam EmailSetting emailsetting, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		System.out.println(emailsetting.getEmail());
		model.addAttribute("user", loggedUser);

		return "settings";
	}

	@RequestMapping(value = "/settings/password", method = RequestMethod.POST)
	public String modifySettings(@RequestParam PasswordSetting password, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		System.out.println(password.getNewPassword());
		model.addAttribute("user", loggedUser);

		return "settings";
	}

	@RequestMapping(value = "/settings/address", method = RequestMethod.POST)
	public String modifySettings(@RequestParam AddressDisplay address, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		System.out.println(address.getCity());
		model.addAttribute("user", loggedUser);

		return "settings";
	}
}

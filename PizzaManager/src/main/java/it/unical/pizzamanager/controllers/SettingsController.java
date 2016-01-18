package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

		model.addAttribute("user", loggedUser);
		model.addAttribute("address", userAddress);
		return "settings";
	}

	
	@ResponseBody
	@RequestMapping(value = "/settings/password", method = RequestMethod.POST)
	public String modifySettings(@RequestParam String passwords, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		String[] toSet = passwords.split(";");
		PasswordSetting password = new PasswordSetting();
		password.setOldPassword(toSet[0]);
		password.setNewPassword(toSet[1]);
		if (loggedUser.getPassword().equals(password.getOldPassword())) {
			loggedUser.setPassword(password.getNewPassword());
			userDAO.update(loggedUser);
			return "{\"redirect\" : index}";
		}
		model.addAttribute("user", loggedUser);

		return "{\"redirect\" : settings}";
	}

	// to fix
	@ResponseBody
	@RequestMapping(value = "/settings/address", method = RequestMethod.POST)
	public String editAddres(@RequestParam String address, Model model, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User loggedUser = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		String[] toSet = address.split(";");
		
		AddressDisplay newAddress=new AddressDisplay();
		if (loggedUser.getPassword().equals(toSet[1])) {
			loggedUser.setAddress(new Address());
			userDAO.update(loggedUser);
			return "{\"redirect\" : index}";
		}
		model.addAttribute("user", loggedUser);
		return "{\"redirect\" : settings}";
	}
}

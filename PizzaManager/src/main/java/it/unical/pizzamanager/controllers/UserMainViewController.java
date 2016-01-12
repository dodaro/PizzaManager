package it.unical.pizzamanager.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.SearchForm;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class UserMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/usermainview", method = RequestMethod.GET)
	public String home() {
		return "usermainview";
	}
/*	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpSession session, @ModelAttribute("searchForm") SearchForm form) {

				UserDAO userDAO = (UserDAO) context.getBean("userDAO");
				User user = userDAO.get(form.getWord());
				if (user != null) {
				SessionUtils.storeUserIdInSession(session, user);
			}
		 else {
			System.out.println("not found");
		}

		return "redirect:/usermainview";
	}*/
}
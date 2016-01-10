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
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriamainview", method = RequestMethod.GET)
	public String home() {
		return "pizzeriamainview";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpSession session, @ModelAttribute("searchForm") SearchForm form) {

				PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
				Pizzeria pizzeria = pizzeriaDAO.get(form.getWord());
				if (pizzeria != null) {
				SessionUtils.storePizzeriaIdInSession(session, pizzeria);
			}
		 else {
			System.out.println("not found");
		}

		return "redirect:/pizzeriamainview";
	}
}
package it.unical.pizzamanager.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.SearchForm;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PizzeriaMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

	@Autowired
	private WebApplicationContext context;

	@RequestMapping(value = "/pizzeriamainview", method = RequestMethod.GET)
	public String pizzeriamainview(@RequestParam Integer id, HttpSession session, Model model) {

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(id);

		setUserAttribute(session, model);
		session.setAttribute("pizzeriaResult", pizzeria);
		model.addAttribute("pizzeriaResult", pizzeria);
		return "pizzeriamainview";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute SearchForm form, HttpSession session, Model model) {

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();
		List<Pizzeria> result = new ArrayList<>();
		for (int i = 0; i < pizzerias.size(); i++) {
			if (pizzerias.get(i).getName().equals(form.getWord())) {
				model.addAttribute("pizzeriaResult", pizzerias.get(i));
				session.setAttribute("pizzeriaResult", pizzerias.get(i));
				setUserAttribute(session, model);
				return "redirect:/pizzeriamainview";
			} else if (pizzerias.get(i).getName().contains(form.getWord()))
				result.add(pizzerias.get(i));
		}
		setUserAttribute(session, model);
		session.setAttribute("pizzeriaResult", result);
		model.addAttribute("pizzeriaResult", result);
		return "resultpage";
	}

	private void setUserAttribute(HttpSession session, Model model) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		model.addAttribute("user", user);
	}
}
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

import it.unical.pizzamanager.forms.FeedbackForm;
import it.unical.pizzamanager.forms.SearchForm;
import it.unical.pizzamanager.persistence.dao.FeedbackDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.FeedbackPizzeria;
import it.unical.pizzamanager.persistence.dto.Pizza;
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
		model.addAttribute("pizzeriaResult", pizzeria);

		if (SessionUtils.isUser(session)) {
			model.addAttribute("userLogged", true);
			model.addAttribute("feedbackForm", new FeedbackForm());
		}

		return "pizzeriamainview";
	}

	@RequestMapping(value = "/pizzeriamainview", method = RequestMethod.POST)
	public String pizzeriamainview(HttpSession session, @RequestParam Integer id,
			@ModelAttribute FeedbackForm form) {

		if (SessionUtils.isUser(session)) {
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

			PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
			Pizzeria pizzeria = pizzeriaDAO.get(id);

			FeedbackDAO feedbackDAO = (FeedbackDAO) context.getBean("feedbackDAO");
			Feedback feedback = new FeedbackPizzeria(user, pizzeria, form.getQuality(),
					form.getFastness(), form.getHospitality(), form.getComment());

			feedbackDAO.create(feedback);
			System.out.println("CREATED");
		}

		return "redirect:/pizzeriamainview?id=" + id;
	}

	@RequestMapping(value = "/pizzeriamainview/booking", method = RequestMethod.POST)
	public String pizzeriamainviewbooking(HttpSession session, @RequestParam Integer id) 
	{
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		System.out.println(id);
		
		return "pizzeriamainview";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute SearchForm form, HttpSession session, Model model) {

		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		List<Pizzeria> pizzerias = pizzeriaDAO.getAll();
		PizzaDAO pizzaDAO = (PizzaDAO) context.getBean("pizzaDAO");
		List<Pizza> pizze = pizzaDAO.getAll();
		List<Pizzeria> result = new ArrayList<>();
		List<Pizza> result2 = new ArrayList<>();
		for (int i = 0; i < pizzerias.size(); i++) {
			if (pizzerias.get(i).getName().contains(form.getWord()))
				result.add(pizzerias.get(i));
		}
		for (int j = 0; j < pizze.size(); j++) {
			if (pizze.get(j).getName().contains(form.getWord()))
				result2.add(pizze.get(j));
		}
		setUserAttribute(session, model);
		model.addAttribute("pizzeriaResult", result);
		model.addAttribute("pizzeriaResult2", result2);

		return "resultpage";
	}

	private void setUserAttribute(HttpSession session, Model model) {
		if (!SessionUtils.isUser(session)) {
			String login = "Login";
			model.addAttribute("typeSession", login);
		} else {
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
			String account = "Account";
			model.addAttribute("typeSession", account);
			model.addAttribute("user", user);
		}
	}
}
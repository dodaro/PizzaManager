package it.unical.pizzamanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.NearbyPizzeriasForm;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dto.Location;
import it.unical.pizzamanager.persistence.dto.Pizzeria;

@Controller
public class PizzeriaController {

	private static final Logger logger = LoggerFactory.getLogger(PizzeriaController.class);

	@Autowired
	private WebApplicationContext context;

	@ResponseBody
	@RequestMapping(value = "/pizzeria/nearby", method = RequestMethod.POST)
	public String getNearbyPizzerias(@ModelAttribute NearbyPizzeriasForm form) {
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Location center = new Location(form.getLatitude(), form.getLongitude());

		List<Pizzeria> pizzeriasNearby = pizzeriaDAO.getPizzeriasWithin(center, form.getRadius());
		return buildNearbyPizzeriasJson(pizzeriasNearby, form.getRadius());
	}

	public String buildNearbyPizzeriasJson(List<Pizzeria> pizzerias, Double radius) {
		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("\"success\" : true");
		builder.append(", ");
		builder.append("\"pizzerias\" : ");
		builder.append("[");

		for (int i = 0; i < pizzerias.size(); i++) {
			builder.append("{");
			builder.append("\"id\" : " + pizzerias.get(i).getId());
			builder.append(", ");
			builder.append("\"name\" : \"" + pizzerias.get(i).getName() + "\"");
			builder.append(", ");
			builder.append("\"latitude\" : " + pizzerias.get(i).getLocation().getLatitude());
			builder.append(", ");
			builder.append("\"longitude\" : " + pizzerias.get(i).getLocation().getLongitude());
			builder.append(", ");
			builder.append("\"street\" : \"" + pizzerias.get(i).getAddress().getStreet() + "\"");
			builder.append(", ");
			builder.append("\"number\" : " + pizzerias.get(i).getAddress().getNumber());
			builder.append(", ");
			builder.append("\"city\" : \"" + pizzerias.get(i).getAddress().getCity() + "\"");
			builder.append("}");

			if (i != pizzerias.size() - 1) {
				builder.append(", ");
			}
		}

		builder.append("]");
		builder.append(", ");
		builder.append("\"radius\" : " + radius);
		builder.append("}");

		return builder.toString();
	}
}

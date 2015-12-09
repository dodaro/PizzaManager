package it.unical.pizzamanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapsController {

	// private static final Logger logger = LoggerFactory.getLogger(MapsController.class);

	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public String home() {
		return "maps";
	}
}

package it.unical.pizzamanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PizzeriaMainViewController {

	private static final Logger logger = LoggerFactory.getLogger(MapsController.class);

	@RequestMapping(value = "/pizzeriamainview", method = RequestMethod.GET)
	public String home() {
		return "pizzeriamainview";
	}
}

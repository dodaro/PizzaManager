package it.unical.pizzamanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {

	// private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String get() {
		return "signup";
	}
}

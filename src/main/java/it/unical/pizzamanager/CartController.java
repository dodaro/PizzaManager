package it.unical.pizzamanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("pizzaCart", getCart());
		return "cart";
	}
	
	List<String> getCart(){
		List<String> cart=new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			cart.add("Pizza"+i);
		}
		return cart;
	}
}

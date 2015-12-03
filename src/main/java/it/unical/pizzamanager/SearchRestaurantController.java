package it.unical.pizzamanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchRestaurantController {

	@RequestMapping(value = "/searchRestaurant", method = RequestMethod.GET)
	public String get(Model model,HttpServletRequest request) {

		int page=Integer.valueOf(request.getParameter("pages"));
		model.addAttribute("pages", page);
		System.out.println(page);
		model.addAttribute("results", getSearchResult(page));
		return "searchRestaurant";
	}

	@RequestMapping(value="/searchRestaurant", method= RequestMethod.POST)
	public String get2(Model model,HttpServletRequest request) {

		int page=Integer.valueOf(request.getParameter("pages"));
		model.addAttribute("pages", page);
		model.addAttribute("results", getSearchResult(page));
		return "searchRestaurant";
	}
	
	List<String> getSearchResult(int page) {
		//handle max num Page
		List<String> results = new ArrayList<String>();
		for (int i = (page-1)*5; i <page*5; i++) {
			results.add("Restaurant" + i);
		}
		return results;
	}

}
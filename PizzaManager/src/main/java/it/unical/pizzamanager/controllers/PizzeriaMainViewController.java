package it.unical.pizzamanager.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.unical.pizzamanager.forms.FeedbackForm;
import it.unical.pizzamanager.forms.SearchForm;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.FeedbackDAO;
import it.unical.pizzamanager.persistence.dao.OrderItemDAO;
import it.unical.pizzamanager.persistence.dao.PizzaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaDAO;
import it.unical.pizzamanager.persistence.dao.PizzeriaTableDAO;
import it.unical.pizzamanager.persistence.dao.RelationPizzeriaPizzaDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.Feedback;
import it.unical.pizzamanager.persistence.dto.FeedbackPizzeria;
import it.unical.pizzamanager.persistence.dto.OrderItem;
import it.unical.pizzamanager.persistence.dto.Pizza;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;
import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;
import it.unical.pizzamanager.persistence.dto.User;
import it.unical.pizzamanager.utils.BookingUtils;
import it.unical.pizzamanager.utils.OrderItemUtils;
import it.unical.pizzamanager.utils.PizzeriaTableUtils;
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
	public String pizzeriamainview(HttpSession session, @RequestParam Integer id, @ModelAttribute FeedbackForm form) {

		if (SessionUtils.isUser(session)) {
			UserDAO userDAO = (UserDAO) context.getBean("userDAO");
			User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));

			PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
			Pizzeria pizzeria = pizzeriaDAO.get(id);

			FeedbackDAO feedbackDAO = (FeedbackDAO) context.getBean("feedbackDAO");
			Feedback feedback = new FeedbackPizzeria(user, pizzeria, form.getQuality(), form.getFastness(),
					form.getHospitality(), form.getComment());

			feedbackDAO.create(feedback);
			System.out.println("CREATED");
		}

		return "redirect:/pizzeriamainview?id=" + id;
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeriamainview/booking", method = RequestMethod.POST)
	public String pizzeriamainviewbooking(@RequestParam int seats, @RequestParam String date, @RequestParam String time,
			@RequestParam int idbooking, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		BookingPizzeriaTable booking = (BookingPizzeriaTable) bookingDAO.getBooking(idbooking);
		PizzeriaTableDAO pizzeriaTableDAO = (PizzeriaTableDAO) context.getBean("pizzeriaTableDAO");
		List<PizzeriaTable> pizzeriaTables = pizzeriaTableDAO.getTablesOfPizzeria(booking.getPizzeria());
		Pizzeria pizzeria = pizzeriaDAO.get(booking.getPizzeria().getId());
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/M/yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
		Date d=null;
		Date t=null;
		try {
			d = sdfDate.parse(date);
			t = sdfTime.parse(time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (d != null && t!=null) {
			List<RelationBookingTablePizzeriaTable> tables = getBookingTables(d, t, seats, pizzeria, booking,
					pizzeriaTables);
			if (tables != null) {

				booking.setDate(d);
				booking.setTime(d);
				bookingDAO.create(booking);
				booking.setTableBooking(tables);
				BookingUtils.calculateBill(booking, booking.getPizzeria());
				bookingDAO.update(booking);

				return "{\"success\" = true}";

			}
		}
		bookingDAO.delete(booking);
		System.out.println("non prenoto");
		return "{\"success\" = false}";
	}

	private List<RelationBookingTablePizzeriaTable> getBookingTables(Date d, Date time, int seats, Pizzeria pizzeria,
			BookingPizzeriaTable mybooking, List<PizzeriaTable> tables) {
		// get only today booking
		List<Booking> bookings = pizzeria.getBookings();
		List<PizzeriaTable> freeTable = PizzeriaTableUtils.getAvailableTables(pizzeria, d, time, context);
		List<RelationBookingTablePizzeriaTable> tablesToBook = new ArrayList<>();
		for (PizzeriaTable pizzeriaTable : freeTable) {
			if (pizzeriaTable.getMaxSeats() > seats) {
				RelationBookingTablePizzeriaTable tab = new RelationBookingTablePizzeriaTable();
				tab.setPizzeriaTable(pizzeriaTable);
				tab.setBooking(mybooking);
				tablesToBook.add(tab);
			}

		}
		// se nessun tavolo può contenere i posti richiesti
		if (tablesToBook.size() == 0) {
			int currSeats = seats;
			for (PizzeriaTable pizzeriaTable : freeTable) {

				RelationBookingTablePizzeriaTable tab = new RelationBookingTablePizzeriaTable();
				tab.setPizzeriaTable(pizzeriaTable);
				tab.setBooking(mybooking);
				tablesToBook.add(tab);
				currSeats -= pizzeriaTable.getMaxSeats();
				if (currSeats <= 0)
					break;

			}
			if (currSeats > 0)
				return null;
		}
		return tablesToBook;
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeriamainview/addPizza", method = RequestMethod.POST)
	public void addPizzaToBook(@RequestParam int idpizza, @RequestParam int idbooking, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		BookingPizzeriaTable booking = (BookingPizzeriaTable) bookingDAO.getBooking(idbooking);
		RelationPizzeriaPizzaDAO pizzaDAO = (RelationPizzeriaPizzaDAO) context.getBean("relationPizzeriaPizzaDAO");
		RelationPizzeriaPizza pizza = pizzaDAO.get(idpizza);
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(booking.getPizzeria().getId());
		if (findItem(idpizza, booking, pizzeria)) {

			return;
		}
		OrderItem item = new PizzaOrderItem();
		item.setBooking(booking);
		item.setNumber(1);
		if (item instanceof PizzaOrderItem)
			((PizzaOrderItem) item).setPizzeria_pizza(pizza);
		item.setCost(pizza.getPrice());

		OrderItemDAO itemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		itemDAO.create(item);
		booking.getOrderItems().add(item);
		bookingDAO.update(booking);
	}

	private boolean findItem(int idpizza, BookingPizzeriaTable booking, Pizzeria pizzeria) {
		OrderItemDAO itemDAO = (OrderItemDAO) context.getBean("orderItemDAO");
		for (OrderItem bookItem : booking.getOrderItems()) {
			if (bookItem instanceof PizzaOrderItem)
				if (((PizzaOrderItem) bookItem).getPizzeria_pizza().getId() == idpizza) {
					bookItem.setNumber(bookItem.getNumber() + 1);
					OrderItemUtils.setPizzaOrderCost((PizzaOrderItem) bookItem, pizzeria);
					itemDAO.update(bookItem);
					return true;
				} else if (bookItem instanceof BeverageOrderItem)
					if (((BeverageOrderItem) bookItem).getPizzeria_beverage().getId() == idpizza) {
						bookItem.setNumber(bookItem.getNumber() + 1);
						OrderItemUtils.setBeverageOrderCost((BeverageOrderItem) bookItem, pizzeria);
						itemDAO.update(bookItem);
						return true;
					}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/pizzeriamainview/createBook", method = RequestMethod.POST)
	public int pizzeriamainviewbooking(@RequestParam int idPizzeria, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		PizzeriaDAO pizzeriaDAO = (PizzeriaDAO) context.getBean("pizzeriaDAO");
		Pizzeria pizzeria = pizzeriaDAO.get(idPizzeria);
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		BookingPizzeriaTable booking = new BookingPizzeriaTable();
		booking.setPizzeria(pizzeria);
		booking.setUser(user);
		bookingDAO.create(booking);

		return booking.getId();

	}

	@ResponseBody
	@RequestMapping(value = "/pizzeriamainview/cancelBook", method = RequestMethod.POST)
	public void cancel(@RequestParam int idbooking, HttpSession session) {
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		Booking booking = (BookingPizzeriaTable) bookingDAO.getBooking(idbooking);

		bookingDAO.delete(booking);

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
package it.unical.pizzamanager.controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import it.unical.pizzamanager.models.BookingUserDisplay;
import it.unical.pizzamanager.models.OrderItemDisplay;
import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.dao.PaymentDAO;
import it.unical.pizzamanager.persistence.dao.UserDAO;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.User;
import it.unical.pizzamanager.utils.BookingUserDisplayUtils;
import it.unical.pizzamanager.utils.GenerateAccessToken;
import it.unical.pizzamanager.utils.SessionUtils;

@Controller
public class PaymentController {

	@Autowired
	WebApplicationContext context;

	@RequestMapping(value = "/payment/return")
	public String confirmPayment(@RequestParam Integer id,@RequestParam String paymentId, HttpServletRequest request,HttpSession session,Model model) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		PaymentDAO paymentDAO=(PaymentDAO) context.getBean("paymentDAO");
		
		model.addAttribute("user",user);
		
		BookingUserDisplayUtils.createPayment(id,bookingDAO,paymentDAO,null);
		
		return "redirect:/orders";
	}


	@RequestMapping(value = "/payment/cancel")
	public String cancelPayment(@RequestParam Integer id,HttpServletRequest request,Model model,HttpSession session) {
		if (!SessionUtils.isUser(session)) {
			return "index";
		}
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = userDAO.get(SessionUtils.getUserIdFromSessionOrNull(session));
		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		PaymentDAO paymentDAO=(PaymentDAO) context.getBean("paymentDAO");
		
		BookingUserDisplayUtils.createPayment(id, bookingDAO, paymentDAO, request.getParameter("token"));
		model.addAttribute("user",user);
		return "redirect:/orders";
	}

	@ResponseBody
	@RequestMapping(value = "/payment/createPayment", method = RequestMethod.POST)
	public String createPayment(@RequestParam Integer bookingId, HttpServletRequest request) {
		Payment createdPayment = null;
		APIContext apiContext = null;
		String accessToken = null;
		try {
			accessToken = GenerateAccessToken.getAccessToken();
			apiContext = new APIContext(accessToken);

		} catch (PayPalRESTException e) {
			return "Failed inizialization";
		}

		BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
		Booking booking = bookingDAO.getBooking(bookingId);
		
		BookingUserDisplay userBooking = BookingUserDisplayUtils.createSimpleBookingUserDisplay(booking);
		List<Transaction> transactions = createBookingTransaction(userBooking);

		// ###Payer
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// ###Payment
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		// ###Redirect URLs
		RedirectUrls redirectUrls = createRedirectUrls(request,bookingId);
		payment.setRedirectUrls(redirectUrls);

		try {
			createdPayment = payment.create(apiContext);
			System.out.println("Created payment with id = " + createdPayment.getId() + " and status = "
					+ createdPayment.getState());
			Iterator<Links> links = createdPayment.getLinks().iterator();
			while (links.hasNext()) {
				Links link = links.next();
				if (link.getRel().equalsIgnoreCase("approval_url")) {
					System.out.println(link.getHref());
					return link.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			return "Failed Creation";
		}

		return "Unknown fail";
	}

	private RedirectUrls createRedirectUrls(HttpServletRequest request,int id) {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/payment/cancel?id="+id);
		redirectUrls.setReturnUrl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/payment/return?id="+id);
		return redirectUrls;
	}

	private static List<Transaction> createBookingTransaction(BookingUserDisplay bookingUserDispay) {
		// ###Details
		// Let's you specify details of a payment amount.
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALIAN);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');
		DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
		Double total = bookingUserDispay.getBill();
		Details details = new Details();
		details.setSubtotal(df.format(total));
		// if (bookingUserDispay.getBookingType().equals("delivery"))
		// details.setShipping("2");

		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		amount.setCurrency("EUR");

		if (bookingUserDispay.getBookingType().equals("delivery"))
			total += 2;
		// Total must be equal to sum of shipping, tax and subtotal.s
		amount.setTotal(df.format(total));
		// amount.setDetails(details);
		System.out.println(df.format(total));
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("This is the payment transaction description.");

		// ### Items
		List<Item> items = new ArrayList<Item>();
		for (OrderItemDisplay it : bookingUserDispay.getItems()) {
			Item item = new Item();
			item.setName(it.getItemName());
			item.setQuantity(String.valueOf(it.getNumber()));
			item.setCurrency("EUR");
			Double itemTotalCost = it.getCost();
			item.setPrice(df.format(itemTotalCost));
			items.add(item);
		}
		ItemList itemList = new ItemList();
		itemList.setItems(items);
		transaction.setItemList(itemList);

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		return transactions;
	}
}

// #Create Payment Using PayPal Sample
// This sample code demonstrates how you can process a 
// PayPal Account based Payment.
// API used: /v1/payments/payment
package it.unical.pizzamanager.paypal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.paypal.base.rest.PayPalResource;

import it.unical.pizzamanager.model.BookingUserDisplay;
import it.unical.pizzamanager.model.OrderItemDisplay;

/**
 * @author lvairamani
 * 
 */
public class PaymentWithPayPal {

	
	// used to history payment
	// Map<String, String> map = new HashMap<String, String>();

	public static void init() {
		// ##Load Configuration
		// Load SDK configuration for the resource.
		// This intialization code can be done as Init Servlet.
		InputStream is = PaymentWithPayPal.class.getResourceAsStream("/sdk_config.properties");
		try {
			PayPalResource.initConfig(is);
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void createPayment(BookingUserDisplay bookingUserDispay, HttpServletRequest req) {
		Payment createdPayment = null;
		// ###AccessToken
		// Retrieve the access token from OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		APIContext apiContext = null;
		String accessToken = null;
		try {
			accessToken = GenerateAccessToken.getAccessToken();
			// ### Api Context
			// Pass in a `ApiContext` object to authenticate the call and to
			// send a unique request id (that ensures idempotency).
			// The SDK generates a request id if you do not pass one explicitly.
			apiContext = new APIContext(accessToken);
		} catch (PayPalRESTException e) {
			// req.setAttribute("error", e.getMessage());
		}

		List<Transaction> transactions = createBookingTransaction(bookingUserDispay);
		// ###Payer
		// A resource representing a Payer that funds a payment Payment Method
		// as 'paypal'
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// ###Payment
		// A Payment Resource; create one using the above types and intent as
		// 'sale'
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		// removed request parameter
		String url = createUrl(createdPayment, apiContext, payment,req);
		bookingUserDispay.setPaypalUrl(url);
		System.out.println(bookingUserDispay.getPaypalUrl());

	}

	private static List<Transaction> createBookingTransaction(BookingUserDisplay bookingUserDispay) {
		// ###Details
		// Let's you specify details of a payment amount.
		System.out.println("create transaction");
		Details details = new Details();
		details.setSubtotal(String.valueOf(bookingUserDispay.getBill()));
		if (bookingUserDispay.getBookingType().equals("delivery"))
			details.setShipping("2");

		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		amount.setCurrency("EUR");
		// Total must be equal to sum of shipping, tax and subtotal.
		if (bookingUserDispay.getBookingType().equals("delivery"))
			amount.setTotal(String.valueOf(bookingUserDispay.getBill() + 2));
		else
			amount.setTotal(String.valueOf(bookingUserDispay.getBill()));

		amount.setDetails(details);

		// ###Transaction
		// A transaction defines the contract of a
		// payment - what is the payment for and who
		// is fulfilling it. Transaction is created with
		// a `Payee` and `Amount` types
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
			item.setPrice(String.valueOf(it.getCost()));
			items.add(item);
		}

		ItemList itemList = new ItemList();
		itemList.setItems(items);

		transaction.setItemList(itemList);

		// The Payment creation API requires a list of
		// Transaction; add the created `Transaction`
		// to a List
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		System.out.println("created transaction");
		return transactions;
	}

	private static String createUrl(Payment createdPayment, APIContext apiContext, Payment payment, HttpServletRequest req) {
		/*
		 * ###Redirect URLs RedirectUrls redirectUrls = new RedirectUrls();
		 * String guid = UUID.randomUUID().toString().replaceAll("-", "");
		 * redirectUrls.setCancelUrl(req.getScheme() + "://" +
		 * req.getServerName() + ":" + req.getServerPort() +
		 * req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
		 * redirectUrls.setReturnUrl(req.getScheme() + "://" +
		 * req.getServerName() + ":" + req.getServerPort() +
		 * req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
		 * payment.setRedirectUrls(redirectUrls);
		 */

		RedirectUrls urls=new RedirectUrls();
		urls.setReturnUrl(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/orders");
		urls.setCancelUrl(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/orders");
		System.out.println(urls.getReturnUrl());
		payment.setRedirectUrls(urls);
		// Create a payment by posting to the APIService using a valid
		// AccessToken. The return object contains the status;
		String url = "";
		try {
			createdPayment = payment.create(apiContext);
			// ###Payment Approval Url
			Iterator<Links> links = createdPayment.getLinks().iterator();
			while (links.hasNext()) {
				Links link = links.next();
				if (link.getRel().equalsIgnoreCase("approval_url")) {
					// req.setAttribute("redirectURL", link.getHref());
					System.out.println(link.getHref());
					url = link.getHref();
				
				}
			}
			// ResultPrinter.addResult(req, resp, "Payment with PayPal",
			// Payment.getLastRequest(), Payment.getLastResponse(), null);
			// map.put(guid, createdPayment.getId());
		} catch (PayPalRESTException e) {
			// ResultPrinter.addResult(req, resp, "Payment with PayPal",
			// Payment.getLastRequest(), null, e.getMessage());
			
		}
		return url;
	}
}

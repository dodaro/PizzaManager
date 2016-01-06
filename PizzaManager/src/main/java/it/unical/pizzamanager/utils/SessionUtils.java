package it.unical.pizzamanager.utils;

import javax.servlet.http.HttpSession;

import it.unical.pizzamanager.persistence.dto.Pizzeria;
import it.unical.pizzamanager.persistence.dto.User;

/**
 * Use this class to interact with the session (get and store attributes, etc.).
 */
public abstract class SessionUtils {

	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_PIZZERIA = "pizzeria";

	public static boolean isLoggedIn(HttpSession session) {
		return isUser(session) || isPizzeria(session);
	}

	public static boolean isPizzeria(HttpSession session) {
		return session.getAttribute(ATTRIBUTE_PIZZERIA) != null;
	}

	public static boolean isUser(HttpSession session) {
		return session.getAttribute(ATTRIBUTE_USER) != null;
	}

	/**
	 * This method returns the session user only if one exists, so check if it returns null or use
	 * the isUser method before.
	 */
	public static User getUserFromSessionOrNull(HttpSession session) {
		return (User) session.getAttribute(ATTRIBUTE_USER);
	}

	/**
	 * This method returns the session pizzeria only if one exists, so check if it returns null or
	 * use the isPizzeria method before.
	 */
	public static Pizzeria getPizzeriaFromSessionOrNull(HttpSession session) {
		return (Pizzeria) session.getAttribute(ATTRIBUTE_PIZZERIA);
	}

	public static void storeUserInSession(HttpSession session, User user) {
		session.setAttribute(ATTRIBUTE_USER, user);
	}

	public static void storePizzeriaInSession(HttpSession session, Pizzeria pizzeria) {
		session.setAttribute(ATTRIBUTE_PIZZERIA, pizzeria);
	}

	public static void clearSession(HttpSession session) {
		session.invalidate();
	}
}
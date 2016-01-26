package it.unical.pizzamanager.utils;

import javax.servlet.http.HttpSession;

import it.unical.pizzamanager.persistence.entities.Pizzeria;
import it.unical.pizzamanager.persistence.entities.User;

/**
 * Use this class to interact with the session (get and store attributes, etc.).
 */
public abstract class SessionUtils {

	private static final String ATTRIBUTE_USER_ID = "user";
	private static final String ATTRIBUTE_PIZZERIA_ID = "pizzeria";

	public static boolean isLoggedIn(HttpSession session) {
		return isUser(session) || isPizzeria(session);
	}

	public static boolean isPizzeria(HttpSession session) {
		return session.getAttribute(ATTRIBUTE_PIZZERIA_ID) != null;
	}

	public static boolean isUser(HttpSession session) {
		return session.getAttribute(ATTRIBUTE_USER_ID) != null;
	}

	/**
	 * This method returns the session user only if one exists, so check if it returns null or use
	 * the isUser method before.
	 */
	public static Integer getUserIdFromSessionOrNull(HttpSession session) {
		return (Integer) session.getAttribute(ATTRIBUTE_USER_ID);
	}

	/**
	 * This method returns the session pizzeria only if one exists, so check if it returns null or
	 * use the isPizzeria method before.
	 */
	public static Integer getPizzeriaIdFromSessionOrNull(HttpSession session) {
		return (Integer) session.getAttribute(ATTRIBUTE_PIZZERIA_ID);
	}

	public static void storeUserIdInSession(HttpSession session, User user) {
		session.setAttribute(ATTRIBUTE_USER_ID, user.getId());
	}

	public static void storePizzeriaIdInSession(HttpSession session, Pizzeria pizzeria) {
		session.setAttribute(ATTRIBUTE_PIZZERIA_ID, pizzeria.getId());
	}

	public static void clearSession(HttpSession session) {
		session.invalidate();
	}
}
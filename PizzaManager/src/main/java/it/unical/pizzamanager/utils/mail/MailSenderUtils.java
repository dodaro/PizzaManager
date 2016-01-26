package it.unical.pizzamanager.utils.mail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.unical.pizzamanager.persistence.entities.Booking;

public class MailSenderUtils {

	private static String FROM="PizzeriaManager.no-reply@gmail.com";
	public static String DELETE="delete";
	public static String NOTIFY="notify";

	public static Boolean SendMail(String subject,String to, Booking booking, String type){
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("pizzamanagerea@gmail.com","enterprise2015");//la password andrebbe messa in un file di configurazione non pushato su git
				}
			});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			if(type.equals(DELETE))
				message.setText(createDeleteMessageFromBooking(booking));
			else if(type.equals(NOTIFY))
				message.setText(createNotifyMessageFromBooking(booking));
			
			Transport.send(message);
			System.out.println("mail to "+to+" is SENT");
			return true;

		} catch (MessagingException e) {
			System.out.println("mail to "+to+" is not sent");
			return false;
			//throw new RuntimeException(e);
		}
	}
	
	private static String createDeleteMessageFromBooking(Booking booking){
		
		String message="Your prenotation in ";
		message+=booking.getPizzeria().getName();
		message+=", in data ";
		message+=booking.getDate().toString();
		message+="at ";
		message+=booking.getTime().toString();
		message+=" has been deleted";
		message+="Greetings!";
		
		return message;
	}
	
	private static String createNotifyMessageFromBooking(Booking booking){
		
		String message="Your prenotation in ";
		message+=booking.getPizzeria().getName();
		message+=", is ready in 30 minutes";
		message+="Greetings!";
		
		return message;
	}
	
}

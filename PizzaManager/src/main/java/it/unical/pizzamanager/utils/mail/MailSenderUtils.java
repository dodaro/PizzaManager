package it.unical.pizzamanager.utils.mail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.unical.pizzamanager.persistence.dto.Booking;

public class MailSenderUtils {

	private static String FROM="PizzeriaManager.no-reply@gmail.com";

	public static void SendMail(String subject,String to, Booking booking){
		
		
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
					return new PasswordAuthentication("pizzamanagerea@gmail.com","enterprise2015");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(createMessageFromBooking(booking));

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String createMessageFromBooking(Booking booking){
		
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
	
}

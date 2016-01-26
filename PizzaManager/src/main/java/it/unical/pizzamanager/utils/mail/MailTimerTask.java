package it.unical.pizzamanager.utils.mail;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import it.unical.pizzamanager.persistence.dao.BookingDAO;
import it.unical.pizzamanager.persistence.entities.Booking;

public class MailTimerTask {
	@Autowired
	private ApplicationContext context;
	
	@Scheduled(fixedDelay = 900000)
    public void demoServiceMethod(){
	
		System.out.println("Method executed at every 15 minutes. Current time is :: "+ new Date());
        BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAO");
        List<Booking> bookings=bookingDAO.getAllBookingListFromData(new Date());
        for (Booking booking : bookings) {
        	if(booking.getNotified()==false && booking.getUser()!=null){
        		//System.out.println(booking.getUser().getEmail() +" "+ booking.getNotified());
        		long diffInMillis = (booking.getTime().getTime() + booking.getDate().getTime()) - new Date().getTime();
        		System.out.println(diffInMillis);
        		if(diffInMillis<=1800000){
        			Boolean sended=MailSenderUtils.SendMail("Pizza notify", booking.getUser().getEmail(), booking,MailSenderUtils.NOTIFY);
        			if(sended){
        				booking.setNotified(true);
        				bookingDAO.update(booking);
        			}
        		}        		
        	}
		}
        
    }
}

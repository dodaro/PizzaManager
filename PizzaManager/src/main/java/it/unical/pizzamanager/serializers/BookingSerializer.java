package it.unical.pizzamanager.serializers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.entities.BeverageOrderItem;
import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.BookingDelivery;
import it.unical.pizzamanager.persistence.entities.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.entities.BookingTakeAway;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;

public class BookingSerializer extends JsonSerializer<Booking>{

	private final String NEW_FORMAT = "dd/MM/yyyy";
	private final String OLD_FORMAT = "yyyy-MM-dd";

	public String convertDate(String oldDataBooking, Booking booking){
		
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date dateBooking=null;
		try {
			dateBooking = sdf.parse(booking.getDate().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf.applyPattern(NEW_FORMAT);
		return sdf.format(dateBooking);
	}
	
	@Override
	public void serialize(Booking booking, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALIAN);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');
		DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
		
		jgen.writeStartObject();
			if(booking.getUser()!=null)
				jgen.writeStringField("user", booking.getUser().getEmail());
			
			if(booking.getBookerName()!=null)
				jgen.writeStringField("underTheNameOf", booking.getBookerName());

			
			
			jgen.writeStringField("date",this.convertDate(booking.getDate().toString(), booking) );
			jgen.writeStringField("time", booking.getTime().toString());
			jgen.writeStringField("id", booking.getId().toString());
			jgen.writeBooleanField("confirmed", booking.getConfirmed());
			jgen.writeStringField("priority", booking.getPriority().toString());
			
			if(booking.getPayment()!=null)
				jgen.writeBooleanField("payment", booking.getPayment().getPaid());
			else
				jgen.writeBooleanField("payment",false);
			
			if(booking instanceof BookingTakeAway)
				jgen.writeStringField("type", "takeAway");
			else if(booking instanceof BookingDelivery){
				BookingDelivery b=(BookingDelivery)booking;
				jgen.writeStringField("type", "delivery");
				jgen.writeObjectFieldStart("address");
					jgen.writeStringField("id",b.getDeliveryAddress().getId().toString());
					jgen.writeStringField("city",b.getDeliveryAddress().getCity());
					jgen.writeStringField("street",b.getDeliveryAddress().getStreet());
					jgen.writeStringField("number",b.getDeliveryAddress().getNumber().toString());
				jgen.writeEndObject();
			}
			else if(booking instanceof BookingPizzeriaTable){
				BookingPizzeriaTable b=(BookingPizzeriaTable)booking;
				jgen.writeStringField("type", "table");
				jgen.writeArrayFieldStart("tables");
				//booking.getpizza() ti restituisce la relationPizzeriaPizza, facendo .getPizza() hai la pizza....
				for (int j = 0; j < b.getTableBooking().size(); j++) {
					jgen.writeStartObject();
						jgen.writeStringField("number",b.getTableBooking().get(j).getPizzeriaTable().getNumber().toString());
						jgen.writeStringField("id",b.getTableBooking().get(j).getPizzeriaTable().getId().toString());
						jgen.writeStringField("seats",b.getTableBooking().get(j).getPizzeriaTable().getSeats().toString());
						jgen.writeStringField("maxSeats",b.getTableBooking().get(j).getPizzeriaTable().getMaxSeats().toString());
						jgen.writeStringField("minSeats",b.getTableBooking().get(j).getPizzeriaTable().getMinSeats().toString());
					jgen.writeEndObject();
				}
				jgen.writeEndArray();
			}
			
			
			jgen.writeArrayFieldStart("pizzas");
				for (int i = 0; i < booking.getOrderItems().size(); i++) {
					if(booking.getOrderItems().get(i) instanceof PizzaOrderItem){
						PizzaOrderItem pizzabooking=(PizzaOrderItem)booking.getOrderItems().get(i);
						Double singlePrice=pizzabooking.getCost()/pizzabooking.getNumber();
						jgen.writeStartObject();
							jgen.writeStringField("priceEach",df.format(singlePrice));
							jgen.writeStringField("name",pizzabooking.getPizzeria_pizza().getPizza().getName());
							jgen.writeStringField("glutenFree",pizzabooking.getPizzeria_pizza().getGlutenFree().toString());
							jgen.writeStringField("size",pizzabooking.getPizzeria_pizza().getPizzaSize().toString());
							jgen.writeStringField("number",pizzabooking.getNumber().toString());
							jgen.writeArrayFieldStart("ingredientsBase");
							//booking.getpizza() ti restituisce la relationPizzeriaPizza, facendo .getPizza() hai la pizza....
							for (int j = 0; j < pizzabooking.getPizzeria_pizza().getPizza().getPizzaIngredients().size(); j++) {
								jgen.writeStartObject();
									jgen.writeStringField("name",pizzabooking.getPizzeria_pizza().getPizza().getPizzaIngredients().get(j).getIngredient().getName());
									jgen.writeStringField("id",pizzabooking.getPizzeria_pizza().getPizza().getPizzaIngredients().get(j).getIngredient().getId().toString());
								jgen.writeEndObject();
							}
							jgen.writeEndArray();
							jgen.writeArrayFieldStart("ingredientsAdded");
							for (int j = 0; j < pizzabooking.getPizzaOrderIngredients().size(); j++) {
								if(pizzabooking.getPizzaOrderIngredients().get(j).getAdditive().equals("addition")){
									jgen.writeStartObject();
										jgen.writeStringField("name",pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getName());
										jgen.writeStringField("id",pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getId().toString());
									jgen.writeEndObject();								
								}
							}
							jgen.writeEndArray();
							jgen.writeArrayFieldStart("ingredientsRemoved");
							for (int j = 0; j < pizzabooking.getPizzaOrderIngredients().size(); j++) {
								if(pizzabooking.getPizzaOrderIngredients().get(j).getAdditive().equals("removal")){
									jgen.writeStartObject();
										jgen.writeStringField("name",pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getName());
										jgen.writeStringField("id",pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getId().toString());
									jgen.writeEndObject();
								}
							}
							jgen.writeEndArray();
						jgen.writeEndObject();
					}
				}
			jgen.writeEndArray();
			jgen.writeArrayFieldStart("beverages");
				for (int i = 0; i < booking.getOrderItems().size(); i++) {
					if(booking.getOrderItems().get(i) instanceof BeverageOrderItem){
						BeverageOrderItem beveragebooking=(BeverageOrderItem)booking.getOrderItems().get(i);
						jgen.writeStartObject();
							jgen.writeStringField("name",beveragebooking.getPizzeria_beverage().getBeverage().getName());
							jgen.writeStringField("brand",beveragebooking.getPizzeria_beverage().getBeverage().getBrand());
							jgen.writeStringField("container",beveragebooking.getPizzeria_beverage().getBeverage().getContainer().toString());
							jgen.writeStringField("size",beveragebooking.getPizzeria_beverage().getBeverage().getSize().toString());
							jgen.writeStringField("type",beveragebooking.getPizzeria_beverage().getBeverage().getType().toString());
							jgen.writeStringField("id",beveragebooking.getPizzeria_beverage().getBeverage().getId().toString());
							jgen.writeStringField("number",beveragebooking.getNumber().toString());
							//ATTENZIONE: per la BEVANDA il prezzo è dato dal price della bibita nella relazione bibitaPizzeria
							//e non dal getCost() dell'ordineBevanda
							jgen.writeStringField("priceEach",(beveragebooking.getPizzeria_beverage().getPrice()).toString());
						jgen.writeEndObject();
						
					}
				}
			jgen.writeEndArray();
			jgen.writeStringField("bill", df.format(booking.getBill()));
		jgen.writeEndObject();
		
	}
}
package it.unical.pizzamanager.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.BookingDelivery;
import it.unical.pizzamanager.persistence.dto.BookingPizzeriaTable;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;

public class BookingSerializer extends JsonSerializer<Booking>{

	@Override
	public void serialize(Booking booking, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		
		
		jgen.writeStartObject();
			//jgen.writeStringField("name", booking.getUser().getEmail());//TODO QUI MI SERVE UN NOME, SPECIE SE SI TRATTA DI UNA PRENOTAZIONE DI UN NON UTENTE
			jgen.writeStringField("date", booking.getDate().toString());
			jgen.writeStringField("time", booking.getTime().toString());
			jgen.writeStringField("id", booking.getId().toString()); //crittografare l'id
			if(booking.getPayment()!=null)
				jgen.writeBooleanField("payment", booking.getPayment().getPaid());
			else
				jgen.writeBooleanField("payment",false);
			
			if(booking instanceof BookingTakeAway)
				jgen.writeStringField("type", "takeAway");
			else if(booking instanceof BookingDelivery){
				BookingDelivery b=(BookingDelivery)booking;
				jgen.writeStringField("type", "delivery");
				jgen.writeStringField("address", b.getDeliveryAddress().toString());
				//TIME TO DELIVERY?
			}
			else if(booking instanceof BookingPizzeriaTable){
				BookingPizzeriaTable b=(BookingPizzeriaTable)booking;
				jgen.writeStringField("type", "table");
				jgen.writeObjectField("table", b.getTableBooking());
			}
			
		
			jgen.writeArrayFieldStart("pizzas");
				for (int i = 0; i < booking.getOrderItems().size(); i++) {
					if(booking.getOrderItems().get(i) instanceof PizzaOrderItem){
						PizzaOrderItem pizzabooking=(PizzaOrderItem)booking.getOrderItems().get(i);
	
						jgen.writeStartObject();
							jgen.writeStringField("name",pizzabooking.getPizzeria_pizza().getPizza().getName());
							jgen.writeStringField("gluten",pizzabooking.getGlutenFree());
							jgen.writeStringField("size",pizzabooking.getSize());
							jgen.writeStringField("number",pizzabooking.getNumber().toString());
							jgen.writeArrayFieldStart("ingredientsBase");
							//booking.getpizza() ti restituisce la relationPizzeriaPizza, facendo .getPizza() hai la pizza....
							for (int j = 0; j < pizzabooking.getPizzeria_pizza().getPizza().getPizzaIngredients().size(); j++) {
								jgen.writeString(pizzabooking.getPizzeria_pizza().getPizza().getPizzaIngredients().get(j).getIngredient().getName());
							}
							jgen.writeEndArray();
							jgen.writeArrayFieldStart("ingredientsAdded");
							for (int j = 0; j < pizzabooking.getPizzaOrderIngredients().size(); j++) {
								if(pizzabooking.getPizzaOrderIngredients().get(j).getAdditive().equals("addition")){
									jgen.writeString(pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getName());									
								}
							}
							jgen.writeEndArray();
							jgen.writeArrayFieldStart("ingredientsRemoved");
							for (int j = 0; j < pizzabooking.getPizzaOrderIngredients().size(); j++) {
								if(pizzabooking.getPizzaOrderIngredients().get(j).getAdditive().equals("removal")){
									jgen.writeString(pizzabooking.getPizzaOrderIngredients().get(j).getIngredient().getName());	
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
							jgen.writeObjectField("beverage",beveragebooking.getPizzeria_beverage().getBeverage());
						jgen.writeEndObject();
						
					}
				}
			jgen.writeEndArray();
		jgen.writeEndObject();
		
	}
	/*
	 * 	booking:{
			name: "giacomino",
			user: "pillo99",  --> nullable
			information:{
				type: "delivery",
				address: "via cerignola",
				date: "12-1-2016, 12.30",
				table: undefined
			},
			pizzas:[{
				number:22, -->numero di pizze ordinate per questa tipologia
				name:"capricciosa",
				ingredients:["banana","tomanto"], -->ingredienti base
				ingredientsAdded:[],
				ingredientsRemoved:["banana"]
			}.{
				..
			}],
			beverages:[{
				number:23, -->numero di bibite ordinate per questa tipologia
				beverage:{
					id:..
					tutti i campi
				} --> crittografato
			}]
		}
	 * */

}

/*COSE DA GESTIRE:
1)il nome del prenotatore nel caso si tratti di una prenotazione che non è fatta da un utente
*/
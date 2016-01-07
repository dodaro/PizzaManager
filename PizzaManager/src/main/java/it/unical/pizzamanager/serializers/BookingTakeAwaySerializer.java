package it.unical.pizzamanager.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.BookingTakeAway;
import it.unical.pizzamanager.persistence.dto.PizzaOrderItem;

public class BookingTakeAwaySerializer extends JsonSerializer<BookingTakeAway>{

	@Override
	public void serialize(BookingTakeAway booking, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		jgen.writeStartObject();
			//jgen.writeStringField("name", booking.getUser().getEmail());//TODO QUI MI SERVE UN NOME, SPECIE SE SI TRATTA DI UNA PRENOTAZIONE DI UN NON UTENTE
			jgen.writeStringField("date", booking.getDate().toString());
			jgen.writeStringField("time", booking.getTime().toString());
			jgen.writeStringField("id", booking.getId().toString()); //crittografare l'id
			if(booking.getPayment()!=null)
				jgen.writeBooleanField("payment", booking.getPayment().getPaid());
			else
				jgen.writeBooleanField("payment",false);
			jgen.writeStringField("type", "takeAway");
			jgen.writeArrayFieldStart("pizzas");
				for (int i = 0; i < booking.getOrderItems().size(); i++) {
					if(booking.getOrderItems().get(i) instanceof PizzaOrderItem){
						PizzaOrderItem pizzabooking=(PizzaOrderItem)booking.getOrderItems().get(i);
						System.out.println(pizzabooking.getPizza().getName());
						jgen.writeStartObject();
							//aggiungere numero di istanze ordinate per questo item
							jgen.writeStringField("name",pizzabooking.getPizza().getName());
							jgen.writeStringField("gluten",pizzabooking.getGlutenFree());
							jgen.writeStringField("size",pizzabooking.getSize());
							jgen.writeStringField("number",pizzabooking.getNumber().toString());
							jgen.writeArrayFieldStart("ingredientsBase");
							for (int j = 0; j < pizzabooking.getPizza().getPizzaIngredients().size(); j++) {
								jgen.writeString(pizzabooking.getPizza().getPizzaIngredients().get(j).getIngredient().getName());
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
							//aggiungere numero di istanze ordinate per questo item
							jgen.writeObjectField("beverage",beveragebooking.getBeverage());
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
			}],
			menus:[{
				number: 21 -->numero di menu ordinati per questa tipologia
				menu:{
					id:..
					tutti i campi
				} --> crittografato
			}]
		}
	 * */

}

/*COSE DA GESTIRE:
1)pizze modificate -ingredienti aggiunti o rimossi, attualmente il database non lo gestisce
2)il nome del prenotatore nel caso si tratti di una prenotazione che non è fatta da un utente
3)all'ordine va aggiunto il numero di pezzi ordinati per quello specifico item
*/
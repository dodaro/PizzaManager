package it.unical.pizzamanager.controllers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class PizzeriaSerializer extends JsonSerializer<Pizzeria>{

	@Override
	public void serialize(Pizzeria pizzeria, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		jgen.writeStartObject();
			jgen.writeArrayFieldStart("pizzas");
				jgen.writeStartObject();
					for (int i = 0; i < pizzeria.getPizzasPriceList().size(); i++) {
						
							jgen.writeStringField("name", pizzeria.getPizzasPriceList().get(i).getPizza().getName());
							jgen.writeArrayFieldStart("ingredients");
								for (int j = 0; j < pizzeria.getPizzasPriceList().get(i).getPizza().getPizzaIngredients().size(); j++) {
									jgen.writeString(pizzeria.getPizzasPriceList().get(i).getPizza().getPizzaIngredients().get(j).getIngredient().getName());
								}
							jgen.writeEndArray();
					}
				jgen.writeEndObject();
			jgen.writeEndArray();
	        jgen.writeStringField("name", pizzeria.getName());
        jgen.writeEndObject();
		
	}
	
	

}

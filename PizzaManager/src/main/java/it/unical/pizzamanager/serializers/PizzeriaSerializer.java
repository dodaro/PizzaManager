package it.unical.pizzamanager.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.Pizzeria;

public class PizzeriaSerializer extends JsonSerializer<Pizzeria> {

	@Override
	public void serialize(Pizzeria pizzeria, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeArrayFieldStart("pizzas");

		for (int i = 0; i < pizzeria.getPizzasPriceList().size(); i++) {
			jgen.writeStartObject();
			jgen.writeStringField("name",
					pizzeria.getPizzasPriceList().get(i).getPizza().getName());
			jgen.writeArrayFieldStart("ingredients");
			for (int j = 0; j < pizzeria.getPizzasPriceList().get(i).getPizza().getPizzaIngredients().size(); j++) {
				jgen.writeStartObject();
					jgen.writeStringField("name",pizzeria.getPizzasPriceList().get(i).getPizza().getPizzaIngredients().get(j).getIngredient().getName());
					jgen.writeStringField("id",pizzeria.getPizzasPriceList().get(i).getPizza().getPizzaIngredients().get(j).getIngredient().getId().toString());
				jgen.writeEndObject();
			}
			jgen.writeEndArray();
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeStringField("name", pizzeria.getName());
		jgen.writeArrayFieldStart("beverages");
		for (int j = 0; j < pizzeria.getBeveragesPriceList().size(); j++) {
			//jgen.writeObject(pizzeria.getBeveragesPriceList().get(j).getBeverage());
			jgen.writeStartObject();
				jgen.writeStringField("name",pizzeria.getBeveragesPriceList().get(j).getBeverage().getName());
				jgen.writeStringField("brand",pizzeria.getBeveragesPriceList().get(j).getBeverage().getBrand());
				jgen.writeStringField("container",pizzeria.getBeveragesPriceList().get(j).getBeverage().getContainer().toString());
				jgen.writeStringField("size",pizzeria.getBeveragesPriceList().get(j).getBeverage().getSize().toString());
				jgen.writeStringField("type",pizzeria.getBeveragesPriceList().get(j).getBeverage().getType().toString());
				jgen.writeStringField("id",pizzeria.getBeveragesPriceList().get(j).getBeverage().getId().toString());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

		jgen.writeArrayFieldStart("allPizzeriaIngredients");
		for (int j = 0; j < pizzeria.getIngredientsPriceList().size(); j++) {
			jgen.writeStartObject();
				jgen.writeStringField("name",pizzeria.getIngredientsPriceList().get(j).getIngredient().getName());
				jgen.writeStringField("id",pizzeria.getIngredientsPriceList().get(j).getIngredient().getId().toString());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

		jgen.writeEndObject();
	}
}

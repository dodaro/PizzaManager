package it.unical.pizzamanager.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.RelationPizzeriaPizza;

public class PizzeriaPizzaSerializer extends JsonSerializer<RelationPizzeriaPizza> {

	@Override
	public void serialize(RelationPizzeriaPizza relation, JsonGenerator jgen,
			SerializerProvider provider) throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", relation.getId());
		jgen.writeNumberField("pizzaId", relation.getPizza().getId());
		jgen.writeStringField("pizzaName", relation.getPizza().getName());
		jgen.writeStringField("size", relation.getPizzaSize().toString());
		jgen.writeStringField("preparationTime",
				getPreparationTimeString(relation.getPreparationTime()));
		jgen.writeBooleanField("glutenFree", relation.getGlutenFree());
		jgen.writeNumberField("price", relation.getPrice());
		jgen.writeEndObject();
	}

	private String getPreparationTimeString(Integer preparationTime) {
		Integer minutes = preparationTime / 60;
		Integer seconds = preparationTime % 60;

		String minutesString = Integer.toString(minutes);
		String secondsString = Integer.toString(seconds);

		if (minutes < 9) {
			minutesString = "0" + minutesString;
		}

		if (seconds < 9) {
			secondsString = "0" + secondsString;
		}

		return minutesString + ":" + secondsString;
	}
}

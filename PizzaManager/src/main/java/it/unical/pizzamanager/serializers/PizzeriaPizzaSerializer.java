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
			jgen.writeNumberField("pizzaId", relation.getPizza().getId());
			jgen.writeStringField("pizzaName", relation.getPizza().getName());
			jgen.writeStringField("size", relation.getPizzaSize().getString());
			jgen.writeNumberField("preparationTime", relation.getPreparationTime());
			jgen.writeBooleanField("glutenFree", relation.getGlutenFree());
			jgen.writeNumberField("price", relation.getPrice());
		jgen.writeEndObject();
	}
}

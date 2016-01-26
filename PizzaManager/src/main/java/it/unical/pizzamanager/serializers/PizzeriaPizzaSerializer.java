package it.unical.pizzamanager.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.entities.Booking;
import it.unical.pizzamanager.persistence.entities.PizzaOrderItem;
import it.unical.pizzamanager.persistence.entities.RelationPizzeriaPizza;

public class PizzeriaPizzaSerializer extends JsonSerializer<RelationPizzeriaPizza> {

	@Override
	public void serialize(RelationPizzeriaPizza relation, JsonGenerator jgen,
			SerializerProvider provider) throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", relation.getId());
		jgen.writeNumberField("pizzaId", relation.getPizza().getId());
		jgen.writeStringField("pizzaName", relation.getPizza().getName());
		jgen.writeStringField("size", relation.getPizzaSize().toString());
		jgen.writeStringField("preparationTime", relation.getPreparationTimeString());
		jgen.writeBooleanField("glutenFree", relation.getGlutenFree());
		jgen.writeNumberField("price", relation.getPrice());
		
		List<PizzaOrderItem> orderItems = relation.getOrderItems();
		List<PizzaOrderItem> activeOrderItems = new ArrayList<>();

		for (PizzaOrderItem o : orderItems) {
			Booking booking = o.getBooking();
			if (booking != null && booking.getCompletionDate() == null) {
				activeOrderItems.add(o);
			}
		}

		jgen.writeNumberField("orderItems", activeOrderItems.size());
		
		jgen.writeEndObject();
	}
}

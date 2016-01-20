package it.unical.pizzamanager.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.BeverageOrderItem;
import it.unical.pizzamanager.persistence.dto.Booking;
import it.unical.pizzamanager.persistence.dto.RelationPizzeriaBeverage;

public class PizzeriaBeverageSerializer extends JsonSerializer<RelationPizzeriaBeverage> {

	@Override
	public void serialize(RelationPizzeriaBeverage relation, JsonGenerator jgen,
			SerializerProvider provider) throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", relation.getId());
		jgen.writeNumberField("beverageId", relation.getBeverage().getId());
		jgen.writeStringField("name", relation.getBeverage().getName());
		jgen.writeStringField("brand", relation.getBeverage().getBrand());
		jgen.writeStringField("container", relation.getBeverage().getContainer().toString());
		jgen.writeStringField("size", relation.getBeverage().getSize().toString());
		jgen.writeStringField("type", relation.getBeverage().getType().toString());
		jgen.writeNumberField("price", relation.getPrice());

		List<BeverageOrderItem> orderItems = relation.getOrderItems();
		List<BeverageOrderItem> activeOrderItems = new ArrayList<>();

		for (BeverageOrderItem o : orderItems) {
			Booking booking = o.getBooking();
			if (booking != null && booking.getCompletionDate() == null) {
				activeOrderItems.add(o);
			}
		}

		jgen.writeNumberField("orderItems", activeOrderItems.size());
		jgen.writeEndObject();
	}
}

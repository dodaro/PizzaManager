package it.unical.pizzamanager.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.PizzeriaTable;
import it.unical.pizzamanager.persistence.dto.RelationBookingTablePizzeriaTable;

public class PizzeriaTableSerializer extends JsonSerializer<PizzeriaTable> {

	@Override
	public void serialize(PizzeriaTable table, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", table.getId());
		jgen.writeNumberField("number", table.getNumber());
		jgen.writeNumberField("seats", table.getSeats());
		jgen.writeNumberField("minSeats", table.getMinSeats());
		jgen.writeNumberField("maxSeats", table.getMaxSeats());
		jgen.writeBooleanField("available", isAvailable(table));
		jgen.writeEndObject();
	}

	private boolean isAvailable(PizzeriaTable table) {
		List<RelationBookingTablePizzeriaTable> bookings = table.getBookings();

		for (RelationBookingTablePizzeriaTable b : bookings) {
			if (b.getBooking().getCompletionDate() == null) {
				return false;
			}
		}

		return true;
	}
}

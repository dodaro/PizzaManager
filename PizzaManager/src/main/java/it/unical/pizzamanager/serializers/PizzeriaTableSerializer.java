package it.unical.pizzamanager.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.unical.pizzamanager.persistence.dto.PizzeriaTable;

public class PizzeriaTableSerializer extends JsonSerializer<PizzeriaTable> {

	@Override
	public void serialize(PizzeriaTable table, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("number", table.getNumber());
		jgen.writeNumberField("sits", table.getSits());
		jgen.writeNumberField("minSits", table.getMinSits());
		jgen.writeNumberField("maxSits", table.getMaxSits());
		jgen.writeBooleanField("available", table.getAvailable());
		jgen.writeEndObject();
	}
}

package it.unical.pizzamanager.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonUtils {

	public static <T> String serializeObject(T object, Class<T> cls, JsonSerializer<T> serializer) {
		ObjectMapper mapper = getMapper(cls, serializer);
		String serialized = null;

		try {
			serialized = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return serialized;
	}

	public static <T> String serializeListAsArray(List<T> objects, Class<T> cls,
			JsonSerializer<T> serializer) {
		StringBuilder serialized = new StringBuilder();
		serialized.append("[");

		for (int i = 0; i < objects.size(); i++) {
			ObjectMapper mapper = getMapper(cls, serializer);

			try {
				serialized.append(mapper.writeValueAsString(objects.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			if (i != objects.size() - 1) {
				serialized.append(",");
			}
		}

		serialized.append("]");
		return serialized.toString();
	}

	private static <T> ObjectMapper getMapper(Class<T> cls, JsonSerializer<T> serializer) {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();

		module.addSerializer(cls, serializer);
		mapper.registerModule(module);

		return mapper;
	}
}

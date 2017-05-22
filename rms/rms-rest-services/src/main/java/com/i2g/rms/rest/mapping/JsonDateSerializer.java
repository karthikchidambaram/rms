package com.i2g.rms.rest.mapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Implementation of {@link JsonSerializer} for serializing a
 * {@link LocalDateTime} instance to a JSON representation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class JsonDateSerializer extends JsonSerializer<LocalDateTime> {

	/** Constant formatter for parsing the date/time. */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");

	@Override
	public void serialize(final LocalDateTime value, final JsonGenerator gen, final SerializerProvider provider)
			throws IOException, JsonProcessingException {
		gen.writeString(value.format(FORMATTER));
	}

}

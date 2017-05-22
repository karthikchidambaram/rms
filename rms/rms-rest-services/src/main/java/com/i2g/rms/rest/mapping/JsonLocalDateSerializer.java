package com.i2g.rms.rest.mapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Implementation of {@link JsonSerializer} for serializing a {@link LocalDate}
 * to a JSON representation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {
	
	/** Constant pattern for formatting a local date. */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	
	@Override
	public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider provider) throws IOException, JsonProcessingException {
		gen.writeString(value.format(FORMATTER));
	}
	
}

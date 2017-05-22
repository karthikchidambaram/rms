package com.i2g.rms.rest.mapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Implementation of {@link JsonDeserializer} for parsing a date into a
 * {@link LocalDate} instance.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {
	
	/** Constant pattern for parsing date. */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	
	
	@Override
	public LocalDate deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final TextNode node = (TextNode) jp.getCodec().readTree(jp);
		return LocalDate.parse(node.textValue(), FORMATTER);
	}
	
}

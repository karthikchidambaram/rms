package com.i2g.rms.rest.mapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Implementation of {@link JsonDeserializer} for parsing a date/time into a
 * {@link LocalDateTime} instance.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class JsonDateDeserializer extends JsonDeserializer<LocalDateTime> {
	
	/** Constant formatter for parsing the date/time. */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	
	@Override
	public LocalDateTime deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final TextNode node = (TextNode) jp.getCodec().readTree(jp);
		return LocalDateTime.parse(node.textValue(), FORMATTER);
	}
	
}

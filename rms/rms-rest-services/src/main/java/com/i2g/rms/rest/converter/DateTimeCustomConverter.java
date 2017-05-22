package com.i2g.rms.rest.converter;

import java.time.LocalDateTime;
import org.dozer.DozerConverter;

public class DateTimeCustomConverter extends DozerConverter<LocalDateTime, LocalDateTime> {

	public DateTimeCustomConverter() {
		super(LocalDateTime.class, LocalDateTime.class);
	}

	@Override
	public LocalDateTime convertTo(final LocalDateTime source, final LocalDateTime destination) {

		if (source == null) {
			return null;
		}

		return LocalDateTime.from(source);
	}

	@Override
	public LocalDateTime convertFrom(final LocalDateTime source, final LocalDateTime destination) {

		if (source == null) {
			return null;
		}

		return LocalDateTime.from(source);
	}

}

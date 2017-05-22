package com.i2g.rms.rest.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.dozer.DozerConverter;

public class DateTimeToStampConverter extends DozerConverter<LocalDateTime, Timestamp> {

	public DateTimeToStampConverter() {
		super(LocalDateTime.class, Timestamp.class);
	}

	@Override
	public LocalDateTime convertFrom(final Timestamp source, final LocalDateTime destination) {

		if (source == null) {
			return null;
		}

		return source.toLocalDateTime();
	}

	@Override
	public Timestamp convertTo(final LocalDateTime source, final Timestamp destination) {

		if (source == null) {
			return null;
		}

		return Timestamp.valueOf(source);
	}

}

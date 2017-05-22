package com.i2g.rms.rest.converter;

import java.time.LocalDate;
import org.dozer.DozerConverter;

/**
 * Custom Converter for LocalDate
 *
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class LocalDateCustomConverter extends DozerConverter<LocalDate, LocalDate> {

	public LocalDateCustomConverter() {
		super(LocalDate.class, LocalDate.class);
	}

	/**
	 * Converts a LocalDate to LocalDate
	 * 
	 * @param source
	 * @return LocalDate
	 */
	@Override
	public LocalDate convertTo(final LocalDate source, final LocalDate destination) {

		if (source == null) {
			return null;
		}

		return LocalDate.from(source);
	}

	/**
	 * Converts a LocalDate to LocalDate
	 * 
	 * @param source
	 * @return LocalDate
	 */
	@Override
	public LocalDate convertFrom(final LocalDate source, final LocalDate destination) {

		if (source == null) {
			return null;
		}

		return LocalDate.from(source);
	}

}

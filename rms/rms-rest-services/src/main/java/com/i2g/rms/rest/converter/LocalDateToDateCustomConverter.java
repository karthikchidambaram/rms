package com.i2g.rms.rest.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.dozer.DozerConverter;

/**
 * Convert a LocalDate to Date and vice-versa
 *
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class LocalDateToDateCustomConverter extends DozerConverter<LocalDate, Date> {
	
	/**
	 * Constructor for LocalDate to/from Date
	 */
	public LocalDateToDateCustomConverter() {
		super(LocalDate.class, Date.class);
	}
	
	/**
	 * Converts a LocalDate to Date
	 * 
	 * @param source
	 * @return Date
	 */
	@Override
	public Date convertTo(final LocalDate source, final Date destination) {
		if (source == null) {
			return null;
		}
		final Instant instant = source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}
	
	/**
	 * Converts a Date to Local
	 * 
	 * @param source
	 * @return LocalDate
	 */
	@Override
	public LocalDate convertFrom(final Date source, final LocalDate destination) {
		if (source == null) {
			return null;
		}
		
		Instant instant = Instant.ofEpochMilli(source.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();		
	 }
	
}

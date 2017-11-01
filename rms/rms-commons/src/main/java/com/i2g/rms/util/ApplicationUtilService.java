package com.i2g.rms.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class ApplicationUtilService extends ApplicationBaseUtilService {

	public static String getUniqueIncidentId() {
		return ApplicationConstants.INCIDENT_ID_PREFIX
				+ new SimpleDateFormat(ApplicationConstants.TIMESTAMP_FORMAT).format(new Date());
	}
	
	public static String getUniqueClaimId() {
		return ApplicationConstants.CLAIM_ID_PREFIX
				+ new SimpleDateFormat(ApplicationConstants.TIMESTAMP_FORMAT).format(new Date());
	}

	public static String getCurrentDateAsString() {
		return new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).format(new Date());
	}

	public static String getCurrentTimestampAsString() {
		return new SimpleDateFormat(ApplicationConstants.TIMESTAMP_FORMAT).format(new Date());
	}

	public static String getCurrentTimeAsString() {
		return new SimpleDateFormat(ApplicationConstants.TIME_FORMAT_24HRS).format(new Date());
	}

	public static long getCurrentTimeAsLong() {
		return new Date().getTime();
	}

	public static long getTimeAsLongFromDate(final Date date) {
		if (date != null) {
			return date.getTime();
		} else {
			return 0l;
		}
	}

	public static String getTimeAsStringFromLong(final Long currentTime) {
		if (currentTime != null) {
			return new SimpleDateFormat(ApplicationConstants.TIME_FORMAT_24HRS).format(new Date(currentTime));
		} else {
			return null;
		}
	}

	public static String getDateAsStringFromLocalDate(final LocalDate localDate) {
		if (localDate != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT);
			return localDate.format(dateTimeFormatter);
		} else {
			return null;
		}
	}
	
	/**
	 * Method that gives the current timestamp
	 * 
	 * @return
	 */
	public static LocalDateTime getCurrentTimestamp() {
		return LocalDateTime.now();
	}
}

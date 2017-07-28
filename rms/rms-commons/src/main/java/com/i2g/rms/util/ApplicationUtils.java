package com.i2g.rms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationUtils extends ApplicationBaseUtils {
	
	public static String getUniqueIncidentId() {
		return ApplicationConstants.INCIDENT_ID_PREFIX + new SimpleDateFormat(ApplicationConstants.TIMESTAMP_FORMAT).format(new Date());
	}
	
	public static String getCurrentDate() {
		return new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).format(new Date());
	}
	
	public static String getCurrentTimestamp() {
		return new SimpleDateFormat(ApplicationConstants.TIMESTAMP_FORMAT).format(new Date());
	}
	
	public static String getCurrentTimeAsString() {
		return new SimpleDateFormat(ApplicationConstants.HRS24_TIME_FORMAT).format(new Date());
	}
	
	public static long getCurrentTimeInLong() {
		return new Date().getTime();
	}
	
	public static String getCurrentTimeFromLong(final long currentTime) {
		return new SimpleDateFormat(ApplicationConstants.HRS24_TIME_FORMAT).format(new Date(currentTime));
	}
}

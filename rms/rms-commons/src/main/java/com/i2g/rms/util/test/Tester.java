package com.i2g.rms.util.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.i2g.rms.util.ApplicationUtils;

public class Tester {
	public static void main (String args[]) throws ParseException {
		long l = 1501489472264l;
		System.out.println("String time from long: " + ApplicationUtils.getTimeAsStringFromLong(l));
		
		System.out.println("###############################");
		
		System.out.println("getUniqueIncidentId: " + ApplicationUtils.getUniqueIncidentId());
		System.out.println("getCurrentDateAsString: " + ApplicationUtils.getCurrentDateAsString());
		System.out.println("getCurrentTimestampAsString: " + ApplicationUtils.getCurrentTimestampAsString());
		System.out.println("getCurrentTimeAsString: " + ApplicationUtils.getCurrentTimeAsString());
		System.out.println("getCurrentTimeAsLong: " + ApplicationUtils.getCurrentTimeAsLong());
		
		String date = "31/08/2016";
		String time = "14:32";
		String dateTime = date + " " + time;		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = df.parse(dateTime);
		System.out.println("String to Date (Default Format): " + d);
		System.out.println("Time as long from Date: " + ApplicationUtils.getTimeAsLongFromDate(d));
		
		System.out.println("getTimeAsStringFromLong: " + ApplicationUtils.getTimeAsStringFromLong(l));
		
		LocalDate ld = LocalDate.now();
		System.out.println("getDateAsStringFromLocalDate: " + ApplicationUtils.getDateAsStringFromLocalDate(ld));
	}
}

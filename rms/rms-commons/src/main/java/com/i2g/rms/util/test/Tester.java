package com.i2g.rms.util.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.i2g.rms.util.ApplicationUtilService;

public class Tester {
	public static void main (String args[]) throws ParseException {
		long l = 1501489472264l;
		System.out.println("String time from long: " + ApplicationUtilService.getTimeAsStringFromLong(l));
		
		System.out.println("###############################");
		
		System.out.println("getUniqueIncidentId: " + ApplicationUtilService.getUniqueIncidentId());
		System.out.println("getCurrentDateAsString: " + ApplicationUtilService.getCurrentDateAsString());
		System.out.println("getCurrentTimestampAsString: " + ApplicationUtilService.getCurrentTimestampAsString());
		System.out.println("getCurrentTimeAsString: " + ApplicationUtilService.getCurrentTimeAsString());
		System.out.println("getCurrentTimeAsLong: " + ApplicationUtilService.getCurrentTimeAsLong());
		
		String date = "31/08/2016";
		String time = "14:32";
		String dateTime = date + " " + time;		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = df.parse(dateTime);
		System.out.println("String to Date (Default Format): " + d);
		System.out.println("Time as long from Date: " + ApplicationUtilService.getTimeAsLongFromDate(d));
		
		System.out.println("getTimeAsStringFromLong: " + ApplicationUtilService.getTimeAsStringFromLong(l));
		
		LocalDate ld = LocalDate.now();
		System.out.println("getDateAsStringFromLocalDate: " + ApplicationUtilService.getDateAsStringFromLocalDate(ld));
	}
}

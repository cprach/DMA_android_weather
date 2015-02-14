package com.cp.dma.utility;

import java.util.Arrays;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.util.Log;

public class ConvertTime {

	private final static String GMT_TIMEZONE = "Etc/GMT";
	private final static String AUST_CITIES[] = {"adelaide","brisbane","canberra","darwin","hobart","melbourne","perth","sydney"};
	private final static String AUST_TIMEZONES[] = {"Australia/Adelaide","Australia/Brisbane","Australia/Canberra","Australia/Darwin","Australia/Hobart","Australia/Melbourne","Australia/Perth","Australia/Sydney"};
	private final static String JSON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String AM = "AM";
	private final static String PM = "PM";
	private final static String NOON = "Noon";
	private final static String MIDNIGHT = "Midnight";
	private final static String CURRENT_DATETIME_FORMAT = "HH:mm";

	
	public static String getCurrentTime() {
		DateTime dt = new DateTime();
		DateTimeFormatter gmtDateTimeFormatter = DateTimeFormat.forPattern(CURRENT_DATETIME_FORMAT);
		
		return dt.toString(gmtDateTimeFormatter);
	}

	private static DateTime convertTime(String JSONDateTime, String cityName) {

		int cityPos = Arrays.binarySearch(AUST_CITIES, cityName.toLowerCase());
		String timezoneId = AUST_TIMEZONES[cityPos];

		DateTimeZone gmtDateTimeZone = DateTimeZone.forID(GMT_TIMEZONE);
		DateTimeZone auMelbDateTimeZone = DateTimeZone.forID(timezoneId);

		DateTimeFormatter gmtDateTimeFormatter = DateTimeFormat.forPattern(JSON_DATETIME_FORMAT).withZone(gmtDateTimeZone);
		
		DateTime gmtDateTime = gmtDateTimeFormatter.parseDateTime(JSONDateTime); 
		DateTime auMelbDateTime = gmtDateTime.toDateTime(auMelbDateTimeZone);
		
		return auMelbDateTime;
	}

	public static String makeDateString(String JSONDateTime, String cityName) {
		
		DateTime dateTime = convertTime(JSONDateTime, cityName);

		String day = dateTime.property(DateTimeFieldType.dayOfWeek()).getAsShortText();
		String dayOfMonth = dateTime.property(DateTimeFieldType.dayOfMonth()).getAsShortText();
		String month = dateTime.property(DateTimeFieldType.monthOfYear()).getAsShortText();
		String year = dateTime.property(DateTimeFieldType.year()).getAsShortText();
		String currentDate = day + " " + dayOfMonth + " " + month + " " + year;		

		return currentDate;
	}

	public static String makeTimeString(String JSONDateTime, String timeZoneID) {

		DateTime dateTime = convertTime(JSONDateTime, timeZoneID);

		String clockHour = null;
		int clHour = dateTime.getHourOfDay();
		if (clHour == 12) {
			clockHour = NOON;
		} else if (clHour == 00) {
			clockHour = MIDNIGHT;
		} else {
			clockHour = dateTime.property(DateTimeFieldType.clockhourOfHalfday()).getAsShortText();
		}

		String ampm = dateTime.property(DateTimeFieldType.halfdayOfDay()).getAsShortText().toLowerCase(Locale.ENGLISH);

		if (ampm.equals(AM)) {
			ampm = AM;
		}
		if (ampm.equals(PM)) {
			ampm = PM;
		}

		String formattedTime = clockHour + " " + ampm;

		return formattedTime;
	}

}




//package com.cp.dma.utility;
//
//import java.util.Arrays;
//import java.util.Locale;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeFieldType;
//import org.joda.time.DateTimeZone;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//
//public class ConvertTime {
//
//	private final static String GMT_TIMEZONE = "Etc/GMT";
//	private final static String AUST_CITIES[] = {"adelaide","brisbane","canberra","darwin","hobart","melbourne","perth","sydney"};
//	private final static String AUST_TIMEZONES[] = {"Australia/Adelaide","Australia/Brisbane","Australia/Canberra","Australia/Darwin","Australia/Hobart","Australia/Melbourne","Australia/Perth","Australia/Sydney"};
//	private final static String JSON_DATETIME_FORMAT = "yyyy-mm-dd HH:mm:ss";
//	private final static String AM = "AM";
//	private final static String PM = "PM";
//	private final static String NOON = "Noon";
//	private final static String MIDNIGHT = "Midnight";
//	private final static String CURRENT_DATETIME_FORMAT = "HH:mm";
//
//	
//	public static String getCurrentTime() {
//		DateTime dt = new DateTime();
//		DateTimeFormatter gmtDateTimeFormatter = DateTimeFormat.forPattern(CURRENT_DATETIME_FORMAT);
//		
//		return dt.toString(gmtDateTimeFormatter);
//	}
//
//	private static DateTime convertTime(String JSONDateTime, String cityName) {
//
//		int cityPos = Arrays.binarySearch(AUST_CITIES, cityName.toLowerCase());
//		String timezoneId = AUST_TIMEZONES[cityPos];
//
//		DateTimeZone gmtDateTimeZone = DateTimeZone.forID(GMT_TIMEZONE);
//		DateTimeZone auMelbDateTimeZone = DateTimeZone.forID(timezoneId);
//
//		DateTimeFormatter gmtDateTimeFormatter = DateTimeFormat.forPattern(JSON_DATETIME_FORMAT).withZone(gmtDateTimeZone);
//		DateTime gmtDateTime = gmtDateTimeFormatter.parseDateTime(JSONDateTime); 
//		DateTime auMelbDateTime = gmtDateTime.toDateTime(auMelbDateTimeZone);
//
//		return auMelbDateTime;
//	}
//
//	public static String makeDateString(String JSONDateTime, String timeZoneID) {
//
//		DateTime dateTime = convertTime(JSONDateTime, timeZoneID);
//
//		String day = dateTime.property(DateTimeFieldType.dayOfWeek()).getAsShortText();
//		String dayOfMonth = dateTime.property(DateTimeFieldType.dayOfMonth()).getAsShortText();
//		String month = dateTime.property(DateTimeFieldType.monthOfYear()).getAsShortText();
//		String year = dateTime.property(DateTimeFieldType.year()).getAsShortText();
//		String currentDate = day + " " + dayOfMonth + " " + month + " " + year;		
//
//		return currentDate;
//	}
//
//	public static String makeTimeString(String JSONDateTime, String timeZoneID) {
//
//		DateTime dateTime = convertTime(JSONDateTime, timeZoneID);
//
//		String clockHour = null;
//		int clHour = dateTime.getHourOfDay();
//		if (clHour == 12) {
//			clockHour = NOON;
//		} else if (clHour == 00) {
//			clockHour = MIDNIGHT;
//		} else {
//			clockHour = dateTime.property(DateTimeFieldType.clockhourOfHalfday()).getAsShortText();
//		}
//
//		String ampm = dateTime.property(DateTimeFieldType.halfdayOfDay()).getAsShortText().toLowerCase(Locale.ENGLISH);
//
//		if (ampm.equals(AM)) {
//			ampm = AM;
//		}
//		if (ampm.equals(PM)) {
//			ampm = PM;
//		}
//
//		String formattedTime = clockHour + " " + ampm;
//
//		return formattedTime;
//	}
//
//}
//

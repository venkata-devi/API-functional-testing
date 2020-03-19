package com.test.api_functional.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarHelper {

	public static String getLocalDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}
	
	public static String getLocalTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH-mm");
		Date date = new Date();
		return (dateFormat.format(date));
	}
	
	

}

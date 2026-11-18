package com.billing.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCurrentDate() {
		Calendar today = Calendar.getInstance();
		return today.getTime();
	}
	
	
	
	public static String getCurrentDateTimeForAttachment(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String current = sdf.format(now);
		current = current.replaceAll("\\ ", "_");
		current = current.replaceAll("\\:", "_");
		current = current.replaceAll("\\.", "_");
		current = current.replaceAll("\\-", "_");
		return "_"+current;
	}
	
	public static String converUiDateToDbDateFormat(String date) throws ParseException{
		return BillingConstants._DD_MM_YYYY.format(BillingConstants._DD_MM_YY.parse(date));
	}
	
	public static String dateFormatForPdf(Date date) throws ParseException{
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}
	
	public static String dateFormatForAutoMail(Date date) throws ParseException{
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
	
	
	public static Date stringToDateFormat(Date date , int day) throws ParseException{
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String dtString = new SimpleDateFormat("dd/MM/yyyy").format(date);
		 Calendar cal = Calendar.getInstance();  
	        try{  
	           cal.setTime(dateFormat.parse(dtString));  
	        }catch(ParseException e){  
	            e.printStackTrace();  
	         }  
	      cal.add(Calendar.DAY_OF_MONTH, day);  
	      String dateAfter = dateFormat.format(cal.getTime());  
		  Date date1=dateFormat.parse(dateAfter);  
		//  System.out.println(date+"---"+day+"---"+dateAfter);
		return date1;
	}
}

package com.billing.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BillingDateHelper {


	public static String getDateFormat(Date datefrmt , String dateFormat) {  // convert Date to  String Date
		String stringDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			stringDate = sdf.format(datefrmt);
		} catch (Exception e) {
			stringDate = sdf1.format(datefrmt);
		}
		return stringDate;
	}
	
	
	public static String getFixedDateFormat(Date datefrmt , String dateFormat) {  // convert Date to  String Date
		String stringDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			stringDate = sdf.format(datefrmt)+"T00:00:00";
		} catch (Exception e) {
		}
		return stringDate;
	}

	public static Date formatDate(String dateStr) {
		Date date = null;
		if (dateStr != null && !dateStr.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US);
			SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
			SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df4 = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				try {
					date = df1.parse(dateStr);
				} catch (ParseException e1) {
					try {
						date = df2.parse(dateStr);
					} catch (ParseException e2) {
						try {
							date = df3.parse(dateStr);
						} catch (ParseException e3) {
							try {
								date = df4.parse(dateStr);
							} catch (ParseException e4) {
								date = null;
							}
						}
					}
				}
			}
		}
		return date;
	}

}

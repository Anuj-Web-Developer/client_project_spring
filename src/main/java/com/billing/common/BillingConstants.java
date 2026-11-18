package com.billing.common;

import java.text.SimpleDateFormat;

public interface BillingConstants {

	int ACTIVE = 1;
	int INACTIVE = 2;
	int DRAFT = 3;
	int SUBMIT = 4;
	int DELETE = 5;	
	int CLOSE = 6;
	int OPEN = 7;
	int PENDING = 8;
	int NOT_SUMITTED  = 9;
	int DONE = 10;
	int UNPAID  = 11;
	int PAID = 12;
	int CANCEL = 13;
	int Approved = 14;
	int Reject = 15;
	
	
	
	
	
  /**************************************/
	
	int Full_Return = 14;
	int Partial_Return = 15;
	int Discount_In_Percentage = 16;
	int Discount_In_Amount = 17;
	int YES = 1;
	int NO = 0;
	
	
	
	  /****************** Service Reminder Module ********************/
	
		int Monthly = 1;
		int Quaterly = 2;
		int Semi_Annually = 3;
		int Anually = 4;
		
		
/***********************Dates Format********************************/
	
	String DISPLAY_DATE_FORMAT1 = "dd/MM/yyyy";
	String DISPLAY_DATE_FORMAT2 = "dd/mm/yy";
	String DISPLAY_DATE_FORMAT3= "dd/MM/yyyy hh:mm aa";
	String DISPLAY_DATE_FORMAT4= "EEE MMM d HH:mm:ss z yyyy";
	String DISPLAY_DATE_FORMAT5= "yyyy-MM-dd";
	String DISPLAY_DATE_FORMAT6= "dd-MMM-yyyy";
	SimpleDateFormat _DD_MM_YY=new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat _DD_MM_YYYY=new SimpleDateFormat("yyyy-MM-dd");
	String DISPLAY_DATE_FORMAT_FIXED= "yyyy-MM-dd";
	
	/*********************** User Access ********************************/
	
		static final int DISPLAY_AS_MENU_Y = 1;
		static final int DISPLAY_AS_MENU_N = 0;
		static final int DISPLAY_AS_MENU_SKIP = 2;
	
	/***********************User Roles ********************************/
		
		static final int ROLE_USER			=1;
		static final int ROLE_PM			=2;
		static final int ROLE_ADMIN			=3;
		
		
		
	
	/***********************User Roles Id********************************/
		static final int ADMIN_ID	   =1;
			
	
	/***********************   API Urls  ********************************/
	    static final String  CUSTOMER_LOGIN_URL = "https://webzent.com/#customer-login";
	
/***********************Document Type For Attachments********************************/
	static String ATTACHMENT_TYPE_DOC 			= "DOC";
	static String ATTACHMENT_TYPE_PIC 			= "PIC";
	
	
/***********************   Upload Path Local  ********************************/
    static final String  Upload_Path  = "D://FullAndFinalProject//TmsServer//src//main//webapp//web_setting//";	
    
    /*********************** Admin Email Id  ********************************/
    
    static final String LocalTestEmail = "ay5245193@gmail.com";
    
 //   static final String adminEmail = "hello@webzent.com";
//    static final Boolean ServerCode = true;
    
    static final Boolean ServerCode = false;
    static final String adminEmail = "anuj.yadav@webzent.com";
	
}

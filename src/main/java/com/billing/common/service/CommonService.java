package com.billing.common.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.billing.login.model.User;

public interface CommonService {


	public MediaType contentType(String keyname);


	public String getClientOS(String lowerCaseBrowser, String browserDetails);

	public String getClientBrowser(String lowerCaseBrowser, String browserDetails);

	public Object findByUsername(String name);

	public String getDocumentActaulNumber(int maxNo, int companyId, String string);

	public int getCurrentYear();

	public int getCurrentMonth();

	public String setFYKeyword(int currentYear, int currentMonth);

	public int getUserId();

	public String getMonthName(int monthId);

	public String getAdminEmail();
	
	public String getUserRole();
	
}

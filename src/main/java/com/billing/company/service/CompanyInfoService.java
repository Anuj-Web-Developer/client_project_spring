package com.billing.company.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.billing.company.model.CompanyInfo;

public interface CompanyInfoService {

	CompanyInfo saveCompanyInfo(CompanyInfo companyInfo);
	
//	List<CompanyInfo> getListOfCompanyInfo();
//	
//	CompanyInfo getCompanyInfoById(Integer id);
	
	String updateCompanyInfo(CompanyInfo companyInfo);

	CompanyInfo getFirstCompanyInfo();
	
	String storeFile(int companyId, String attachmentType, MultipartFile file);
	
	ResponseEntity<byte[]> getAttachment(int companyId, String attachmentType);
}

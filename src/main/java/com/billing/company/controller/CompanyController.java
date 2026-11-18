package com.billing.company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.billing.company.model.CompanyInfo;
import com.billing.company.service.CompanyInfoService;

@RestController
@RequestMapping("/api/company-info")
public class CompanyController {

	@Autowired
	private CompanyInfoService companyInfoServ;

//	******************************* Save Company-info **********************************************************
	@PostMapping("/saveCompany")
	public ResponseEntity<?> saveCompanyController(@Valid @RequestBody CompanyInfo companyInfo) {
		
		return ResponseEntity.ok(companyInfoServ.saveCompanyInfo(companyInfo));
	}

//	******************************* Get Company-list **********************************************************
//	@GetMapping("/getCompanyList")
//	public ResponseEntity<List<CompanyInfo>> getCompanyListController() {
//		
//		return ResponseEntity.ok(companyInfoServ.getListOfCompanyInfo());
//	}

//	******************************* Get Company-By-Id *******************************************************
//	@GetMapping("/getCompanyById/{id}")
//	public ResponseEntity<?> getCompanyByIdController(@PathVariable Integer id) {
//		
//		return ResponseEntity.ok(companyInfoServ.getCompanyInfoById(id));
//	}

//	******************************* Update Company-Info *******************************************************
//	@PutMapping("/updateCompanyInfo")
//	public ResponseEntity<?> updateCompanyInfoController(@Valid @RequestBody CompanyInfo companyInfo) {
//		
//		return ResponseEntity.ok(companyInfoServ.updateCompanyInfo(companyInfo));
//	}

//	******************************* Get First Company-Info *******************************************************
	@GetMapping("/getCompanyInfo")
	public ResponseEntity<?> getFirstCompanyInfoController() {

		return ResponseEntity.ok(companyInfoServ.getFirstCompanyInfo());
	}

	
//	******************************* Save Attachment *******************************************************
	@PostMapping("/saveFile")
	public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id,
			@RequestParam("attachmentType") String attachmentType) {

		return ResponseEntity.ok(companyInfoServ.storeFile(id, attachmentType, file));
	}
	
	
//	******************************* View Attachment *******************************************************
	@GetMapping("/viewFile/{companyId}/{fileType}")
	public ResponseEntity<byte[]> viewFile(@PathVariable int companyId, @PathVariable String fileType) {
		return companyInfoServ.getAttachment(companyId, fileType);
	}

}

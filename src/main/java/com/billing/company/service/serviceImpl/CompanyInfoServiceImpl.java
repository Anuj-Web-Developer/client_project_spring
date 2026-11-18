package com.billing.company.service.serviceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.billing.company.model.CompanyInfo;
import com.billing.company.repository.CompanyInfoRepository;
import com.billing.company.service.CompanyInfoService;
import com.billing.project.entity.Project;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService{
	
	@Autowired
	private CompanyInfoRepository companyInfoRepo;
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
		
		return companyInfoRepo.save(companyInfo);
	}
	
	
//	public List<CompanyInfo> getListOfCompanyInfo() {
//		
//		return companyInfoRepo.findAll();
//	}
//	
//	
//	public CompanyInfo getCompanyInfoById(Integer id) {
//
//	    CompanyInfo company = companyInfoRepo
//	            .findById(id)
//	            .orElseThrow(() -> new RuntimeException("Company not found"));
//
//	    return company;
//	}
	
	
	public String updateCompanyInfo(CompanyInfo companyInfo) {
		
		companyInfoRepo.save(companyInfo);
		return "Company updated successfully";
	}


	@Override
	public CompanyInfo getFirstCompanyInfo() {
		
		CompanyInfo companyInfo = companyInfoRepo.findFirstBy().orElseThrow(() -> new RuntimeException("Company Information not found"));
		return companyInfo;
	}
	
	
//	********************************** Save Attachment **************************************************
	@Override
	public String storeFile(int companyId, String attachmentType, MultipartFile file) {

		try {

			if (file == null || file.isEmpty()) {
				throw new RuntimeException("File is empty");
			}

			// Get application physical path
			String realPath = servletContext.getRealPath("/");

			if (realPath == null) {
				throw new RuntimeException("Application real path is not available");
			}

			// Create upload directory
			Path uploadDirectory = Paths.get(realPath, "company_doc");

			Files.createDirectories(uploadDirectory);

			// Original file name
			String originalFileName = file.getOriginalFilename();

			// Get extension
			String extension = "";

			if (originalFileName != null && originalFileName.contains(".")) {

				extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			}

			// Generate unique file name
			String uniqueFileName = System.currentTimeMillis() + extension;

			// Final file location
			Path destination = uploadDirectory.resolve(uniqueFileName);

			// Save file
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

			// Find Company
			CompanyInfo company = companyInfoRepo.findById(companyId)
					.orElseThrow(() -> new RuntimeException("CompanyInfo not found"));

			// Save file name/path according to attachment type
			switch (attachmentType) {

			case "logoName":
				company.setLogoName(uniqueFileName);
				break;

			case "singnatureName":
				company.setSingnatureName(uniqueFileName);
				break;

			default:
				throw new RuntimeException("Invalid attachment type: " + attachmentType);
			}

			companyInfoRepo.save(company);

			// Return file name
			return uniqueFileName;

		} catch (IOException e) {

			throw new RuntimeException("Could not store file", e);
		}
	}
	
	
//	********************************** View Attachment **************************************************
	@Override
	public ResponseEntity<byte[]> getAttachment(int companyId, String attachmentType) {

	    try {

	        // Find company
	        CompanyInfo company = companyInfoRepo.findById(companyId)
	                .orElseThrow(() -> new RuntimeException("CompanyInfo not found"));

	        // Get filename according to attachment type
	        String fileName;

	        switch (attachmentType) {

	        case "logoName":
	            fileName = company.getLogoName();
	            break;

	        case "singnatureName":
	            fileName = company.getSingnatureName();
	            break;

	        default:
	            throw new RuntimeException("Invalid attachment type: " + attachmentType);
	        }

	        // Check attachment exists
	        if (fileName == null || fileName.isBlank()) {
	            throw new RuntimeException("Attachment Not Found");
	        }

	        // Get application real path
	        String realPath = servletContext.getRealPath("/");

	        if (realPath == null) {
	            throw new RuntimeException("Application real path is not available");
	        }

	        // Build and validate file path (prevents path traversal)
	        Path uploadDirectory = Paths.get(realPath, "company_doc").normalize().toAbsolutePath();
	        Path filePath = uploadDirectory.resolve(fileName).normalize();

	        if (!filePath.startsWith(uploadDirectory)) {
	            throw new RuntimeException("Invalid file path");
	        }

	        // Check file exists
	        if (!Files.exists(filePath)) {
	            throw new RuntimeException("File Not Found");
	        }

	        // Read file
	        byte[] fileData = Files.readAllBytes(filePath);

	        // Detect content type
	        String contentType = Files.probeContentType(filePath);

	        MediaType mediaType;

	        try {
	            mediaType = contentType != null ? MediaType.parseMediaType(contentType)
	                    : MediaType.APPLICATION_OCTET_STREAM;
	        } catch (Exception e) {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

	        // Build Content-Disposition (inline for viewing, e.g. logo preview in browser)
	        ContentDisposition disposition = ContentDisposition.builder("inline")
	                .filename(fileName, StandardCharsets.UTF_8)
	                .build();

	        return ResponseEntity.ok()
	                .contentType(mediaType)
	                .contentLength(fileData.length)
	                .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString())
	                .body(fileData);

	    } catch (IOException e) {
	        throw new RuntimeException("Could not fetch file", e);
	    }
	}
	
}

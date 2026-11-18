package com.billing.attachment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.billing.attachment.entity.Attachment;
import com.billing.attachment.repository.AttachmentRepository;

@Transactional
@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepo;

	public Attachment storeFile(MultipartFile file) {

		try {

			if (file == null || file.isEmpty()) {
				throw new RuntimeException("File is empty");
			}

			Path uploadDirectory = Paths.get("D:/billing-uploads");

			Files.createDirectories(uploadDirectory);

			String originalFileName = file.getOriginalFilename();

			String extension = "";

			if (originalFileName != null && originalFileName.contains(".")) {

				extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			}

			String uniqueFileName = UUID.randomUUID() + extension;

			Path destination = uploadDirectory.resolve(uniqueFileName);

			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

			Attachment attachment = new Attachment();

			attachment.setFileName(file.getOriginalFilename());

			attachment.setFileType(file.getContentType());

			attachment.setFilePath(destination.toString());

			attachmentRepo.save(attachment);

			return attachment;

		} catch (IOException e) {

			throw new RuntimeException("Could not store file", e);
		}
	}

//	public Attachment getAttachmentByProjectId(Integer id) {
//		return attachmentRepo.getByProjectId(id);
//	}

	public ResponseEntity<byte[]> downloadFileService(Integer attachmentId) {

		try {

			// Find attachment from database
			Attachment attachment = attachmentRepo.findById(attachmentId)
					.orElseThrow(() -> new RuntimeException("Attachment Not Found"));

			// Get actual file path from database
			Path filePath = Paths.get(attachment.getFilePath());

			// Check file exists
			if (!Files.exists(filePath)) {
				throw new RuntimeException("File Not Found");
			}

			// Read actual file data from D drive
			byte[] fileData = Files.readAllBytes(filePath);

			// Get file type
			MediaType mediaType;

			try {
				mediaType = MediaType.parseMediaType(attachment.getFileType());
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}

			// Return actual file data
			return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + attachment.getFileName() + "\"").body(fileData);

		} catch (IOException e) {

			throw new RuntimeException("Could not download file", e);
		}
	}
		

//	********************************* View Attachment **************************************************************
	public ResponseEntity<byte[]> viewFileService(Integer attachmentId) {

		try {

			Attachment attachment = attachmentRepo.findById(attachmentId)
					.orElseThrow(() -> new RuntimeException("Attachment Not Found"));

			Path filePath = Paths.get(attachment.getFilePath());

			if (!Files.exists(filePath)) {
				throw new RuntimeException("File Not Found");
			}

			byte[] fileData = Files.readAllBytes(filePath);

			MediaType mediaType;

			try {

				mediaType = MediaType.parseMediaType(attachment.getFileType());

			} catch (Exception e) {

				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}

			return ResponseEntity.ok().contentType(mediaType)
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getFileName() + "\"")
					.body(fileData);

		} catch (IOException e) {

			throw new RuntimeException("Could not view file", e);
		}
	}

}

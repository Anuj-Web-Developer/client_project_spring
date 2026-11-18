package com.billing.attachment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.billing.attachment.service.AttachmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentServ;

	@PostMapping("/saveFile")
	public ResponseEntity<?> saveFileController(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(attachmentServ.storeFile(file));
	}

	@GetMapping("/download/{attachmentId}")
	public ResponseEntity<byte[]> downloadFileController(@PathVariable int attachmentId) {

		return attachmentServ.downloadFileService(attachmentId);
	}

//	********************************* View Attachment ********************************************************
	@GetMapping("/viewAttachment/{attachmentId}")
	public ResponseEntity<byte[]> viewFileController(@PathVariable int attachmentId) {
		System.out.println("**********************************************************");
		System.out.println("AttachmentId is: "+attachmentId);
		return attachmentServ.viewFileService(attachmentId);
	}
	
	
}

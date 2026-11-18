package com.billing.project.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.billing.attachment.entity.Attachment;
import com.billing.attachment.repository.AttachmentRepository;
import com.billing.enumeration.Status;
import com.billing.exception.ResourceNotFoundException;
import com.billing.project.dto.ProjectInfo;
import com.billing.project.dto.ProjectListDTO;
import com.billing.project.entity.Project;
import com.billing.project.repository.ProjectRepository;
import com.billing.project.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private ServletContext servletContext;

	@Override
	public Project saveProjectService(Project project) {

		return projectRepo.save(project);
	}

	@Override
	public ResponseEntity<?> editProjectService(Project project) {

		projectRepo.save(project);
		return ResponseEntity.ok("Project Detail updated successfully");
	}

	public List getProjectListService() {
		return projectRepo.findAllByOrderByIdDsc();
	}

	@Override
	public ResponseEntity<?> getProjectByIdService(int id) {
		Optional<Project> projectOpt = projectRepo.findById(id);
		if (projectOpt.isPresent()) {
			return ResponseEntity.ok(projectOpt.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found with id: " + id);
		}
	}

	@Override
	public Project updateProject(Project project) {

		return projectRepo.save(project);
	}

	@Override
	public Long countProjectService() {
		return projectRepo.count();
	}

	@Override
	public ResponseEntity<?> findByCustomerIdService(int id) {
		List<Project> projects = projectRepo.findByCustomerId(id);
		if (projects.size() == 0) {
			return ResponseEntity.ok().body("List is Empty");
		}
		return ResponseEntity.ok().body(projects);
	}

	@Override
	public List<?> getProjectInfoList() {
		List<Project> list = new ArrayList<>();
		list = projectRepo.findAll();
		return list;
	}

	@Override
	public List<Project> getFilteredProjectInfoList(LocalDate from, LocalDate to, Status status) {
		List<Project> list = projectRepo.findFilteredProjects(from, to, status);
		return list;
	}

//	************************* Attachment Code ********************************

	public String storeFile(int projectId, String attachmentType, MultipartFile file) {

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
			Path uploadDirectory = Paths.get(realPath, "project_doc");

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

			// Find project
			Project project = projectRepo.findById(projectId)
					.orElseThrow(() -> new RuntimeException("Project not found"));

			// Save file name/path according to attachment type
			switch (attachmentType) {

			case "loginAttachment":
				project.setLoginAttachment(uniqueFileName);
				break;

			case "serverAttachment1":
				project.setServerAttachment1(uniqueFileName);
				break;

			case "serverAttachment2":
				project.setServerAttachment2(uniqueFileName);
				break;

			default:
				throw new RuntimeException("Invalid attachment type: " + attachmentType);
			}

			projectRepo.save(project);

			// Return file name
			return uniqueFileName;

		} catch (IOException e) {

			throw new RuntimeException("Could not store file", e);
		}
	}


	public ResponseEntity<byte[]> downloadFileService(Integer projectId, String attachmentType) {

		try {

			// Find project
			Project project = projectRepo.findById(projectId)
					.orElseThrow(() -> new RuntimeException("Project Not Found"));

			// Get filename according to attachment type
			String fileName;

			switch (attachmentType) {

			case "loginAttachment":
				fileName = project.getLoginAttachment();
				break;

			case "serverAttachment1":
				fileName = project.getServerAttachment1();
				break;

			case "serverAttachment2":
				fileName = project.getServerAttachment2();
				break;

			default:
				throw new RuntimeException("Invalid attachment type: " + attachmentType);
			}

			// Check attachment exists in project
			if (fileName == null || fileName.isBlank()) {
				throw new RuntimeException("Attachment Not Found");
			}

			// Get application real path
			String realPath = servletContext.getRealPath("/");

			if (realPath == null) {
				throw new RuntimeException("Application real path is not available");
			}

			// Build actual file path
			Path filePath = Paths.get(realPath, "project_doc", fileName);

			// Check file exists
			if (!Files.exists(filePath)) {
				throw new RuntimeException("File Not Found");
			}

			// Read file
			byte[] fileData = Files.readAllBytes(filePath);

			// Detect file type
			String contentType = Files.probeContentType(filePath);

			MediaType mediaType;

			try {
				mediaType = contentType != null ? MediaType.parseMediaType(contentType)
						: MediaType.APPLICATION_OCTET_STREAM;
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}

			// Return file
			return ResponseEntity.ok().contentType(mediaType)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
					.body(fileData);

		} catch (IOException e) {

			throw new RuntimeException("Could not download file", e);
		}
	}


}

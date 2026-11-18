package com.billing.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.billing.attachment.service.AttachmentService;
import com.billing.common.service.serviceImpl.CommonServiceImpl;
import com.billing.enumeration.Status;
import com.billing.exception.ResourceNotFoundException;
import com.billing.login.model.User;
import com.billing.login.repository.UserRepository;
import com.billing.otp.model.EmailDTO;
import com.billing.otp.service.EmailService;
import com.billing.project.entity.Project;
import com.billing.project.repository.ProjectRepository;
import com.billing.project.service.ProjectService;
import com.billing.project.serviceImpl.ProjectServiceImpl;

@CrossOrigin("*")
@RequestMapping("/api/project")
@RestController
public class ProjectController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailService emailServ;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CommonServiceImpl commonServ;

	// Save project
	@PostMapping("/saveProject")
	public ResponseEntity<?> saveProjectController(@Valid @RequestBody Project project) {
		
		Project db_project;

		User user = commonServ.getCurrentUser();

		if (user.getIsAllowedCreateProject()) {
			db_project = projectService.saveProjectService(project);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't add new project.");
		}

		if (project.getIsSendEmail()) {

			EmailDTO emailDto = new EmailDTO();

			emailDto.setUserEmail(user.getEmail());
			emailDto.setSms(
					"Your project has been added to our portal, where you can securely access your Project login credentials.");
			emailDto.setCustomerEmail(project.getCustomer().getEmail());
			emailDto.setCustomerName(project.getCustomer().getName());
			emailDto.setTime(LocalDateTime.now());
			emailDto.setSubject("Project Created Successfully");
			emailDto.setProjectName(project.getProjectName());

			emailServ.sendProjectEmail(emailDto);
//			return ResponseEntity.ok("Project has been updated successfully");

		}
		return ResponseEntity.ok(db_project);
	}

	// Get Project List
	@GetMapping("/getProjectList")
	public ResponseEntity<?> getProjectListController(Authentication auth) {

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		String username = auth.getName();

		String role = auth.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);

		if (role.equals("ROLE_USER")) {
			User user = userRepo.findByUsername(username).get();
			List<Project> project_list = user.getProject();
			return ResponseEntity.ok(project_list);
		} else {
			return ResponseEntity.ok(projectService.getProjectListService());
		}
	}

	// Get Project By Id
	@GetMapping("/getProject/{id}")
	public ResponseEntity<?> getProjectByIdController(@PathVariable int id) {
		return projectService.getProjectByIdService(id);
	}

	// Update project details
//	@PutMapping("/updateProject")
	@PostMapping("/updateProject")
	public ResponseEntity<?> updateProjectController(@Valid @RequestBody Project project, Authentication auth) {

		Project db_project = projectService.updateProject(project);

		if (project.getIsSendEmail()) {

			EmailDTO emailDto = new EmailDTO();

			emailDto.setUserEmail(userRepo.findByUsername(auth.getName()).get().getEmail());
			emailDto.setSms(
					"Your project details have been updated successfully. You can log in to your client portal using the same login credentials to view the latest project information.");
			emailDto.setCustomerEmail(project.getCustomer().getEmail());
			emailDto.setCustomerName(project.getCustomer().getName());
			emailDto.setTime(LocalDateTime.now());
			emailDto.setSubject("Project Updated Successfully");
			emailDto.setProjectName(project.getProjectName());

			emailServ.sendProjectEmail(emailDto);
			return ResponseEntity.ok(db_project);

		}
		return ResponseEntity.ok(db_project);

	}

	// Count total project
	@GetMapping("/countProject")
	public Long countProjectController() {
		return projectService.countProjectService();
	}

	// Get project by customer-id
	@GetMapping("/findByCustomerId/{id}")
	public ResponseEntity<?> getProjectbyCustomerIdController(@PathVariable int id) {
		return projectService.findByCustomerIdService(id);
	}

	@GetMapping("/getProjectInfo")
	public ResponseEntity<?> getProjectInfoListController() {
		return ResponseEntity.ok(projectService.getProjectInfoList());
	}

	@GetMapping("/getFilteredProjectInfo")
	public ResponseEntity<?> getFilteredProjectInfoListController(

			@RequestParam(required = false) Status status,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

		List<Project> list = projectService.getFilteredProjectInfoList(from, to, status);

		return ResponseEntity.ok(list);
	}

	@PostMapping("/sendReminder/{projectId}")
	public ResponseEntity<?> sendReminderController(@PathVariable Integer projectId, Authentication auth) {

		String customerName = projectRepo.findById(projectId).get().getCustomer().getName();
		String customerEmail = projectRepo.findById(projectId).get().getCustomer().getEmail();
		String userEmail = userRepo.findByUsername(auth.getName()).get().getEmail();

		EmailDTO emailDto = new EmailDTO();

		emailDto.setCustomerName(customerName);
		emailDto.setCustomerEmail(customerEmail);
		emailDto.setUserEmail(userEmail);
		emailDto.setSubject("Your Project Login Details Has Been Updated");
		emailDto.setSms(
				"This is a quick reminder to access your client portal to view your latest Project login details.");

		return ResponseEntity.ok(emailServ.sendReminder(emailDto));
	}

//	********************* Attachment controller code ********************************
	@PostMapping("/saveFile")
	public ResponseEntity<?> saveFileController(@RequestParam("file") MultipartFile file,
			@RequestParam("projectId") int projectId, @RequestParam("attachmentType") String attachmentType) {
		
		System.out.println("*********** Save file controller method called*********** ");
		System.out.println("Project id is: "+projectId);
		
		return ResponseEntity.ok(projectService.storeFile(projectId, attachmentType, file));
	}

	
//	********************************* Download Attachment ********************************************************
	@GetMapping("/download/{attachmentId}/{attachmentType}")
	public ResponseEntity<byte[]> downloadFileController(@PathVariable int attachmentId, @PathVariable String attachmentType) {

		return projectService.downloadFileService(attachmentId, attachmentType);
	}

	
//	********************************* View Attachment ********************************************************
//	@GetMapping("/viewAttachment/{attachmentId}")
//	public ResponseEntity<byte[]> viewFileController(@PathVariable int attachmentId) {
//		System.out.println("**********************************************************");
//		System.out.println("AttachmentId is: "+attachmentId);
//		return projectService.viewFileService(attachmentId);
//	}

}

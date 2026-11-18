package com.billing.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.billing.attachment.entity.Attachment;
import com.billing.enumeration.Status;
import com.billing.project.dto.ProjectListDTO;
import com.billing.project.entity.Project;

public interface ProjectService {

	Project saveProjectService(Project project);

	ResponseEntity<?> editProjectService(Project project);

	List<ProjectListDTO> getProjectListService();

	ResponseEntity<?> getProjectByIdService(int id);

	Project updateProject(Project project);

	Long countProjectService();

	ResponseEntity<?> findByCustomerIdService(int id);

	List<?> getProjectInfoList();

	List<Project> getFilteredProjectInfoList(LocalDate from, LocalDate to, Status staus);

//	******************************** Attachment Methods *******************************************

	String storeFile(int projectId, String attachmentType, MultipartFile file);
	
	ResponseEntity<byte[]> downloadFileService(Integer projectId, String attachmentType);
	
//	ResponseEntity<byte[]> viewFileService(Integer projectId);

}

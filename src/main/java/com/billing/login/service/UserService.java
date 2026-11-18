package com.billing.login.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.billing.enumeration.Status;
import com.billing.exception.ResourceNotFoundException;
import com.billing.logMgmt.model.LoginLogs;
import com.billing.login.model.User;
import com.billing.project.entity.Project;
import com.billing.urlAccess.bean.MenuUrlBean;
import com.billing.urlAccess.bean.MenuUserMappingBean;

public interface UserService {

	List<User> getUserList();

	User getById(long userId) throws ResourceNotFoundException;

	List<User> getUserListForlogs();

	List<MenuUrlBean> getMenuUrlList();

	List<MenuUserMappingBean> getMenuMappingList(Integer userId);

	void saveAllSelected(@Valid int[] permissionIds);
	
	Long countUserService();
	
	List<?> getLoginLogsService();
	
	List<?> getFilteredLoginLogsService(String name, LocalDate from, LocalDate to);

	List<Project> findProjectListByUserId(Long id, LocalDate from, LocalDate to, Status status);
	
	 List<?> getListofName();
	 
	 ResponseEntity<String> editUserService(User user);
	
}

package com.billing.login.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.billing.enumeration.Status;
import com.billing.exception.ResourceNotFoundException;
import com.billing.logMgmt.model.LoginLogs;
import com.billing.logMgmt.repository.LoginLogRepository;
import com.billing.login.model.User;
import com.billing.login.repository.UserRepository;
import com.billing.login.service.UserService;
import com.billing.project.entity.Project;
import com.billing.project.repository.ProjectRepository;
import com.billing.urlAccess.bean.MenuUrlBean;
import com.billing.urlAccess.bean.MenuUserMappingBean;
import com.billing.urlAccess.repository.MenuUrlBeanRepository;
import com.billing.urlAccess.repository.MenuUserMappingBeanRepository;

@Service
@Validated
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MenuUrlBeanRepository menuRepository;
	
	@Autowired
	MenuUserMappingBeanRepository menuMappingRepository;
	
	@Autowired
	private LoginLogRepository logsRepo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUserList() {
		return userRepository.findAllByOrderByIdDsc();	
	}

	@Override
	public User getById(long userId) throws ResourceNotFoundException {
		return userRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("User not found for this id :: " + userId));
	}

	@Override
	public List<User> getUserListForlogs() {
		return userRepository.findAll();
	}

	@Override
	public List<MenuUrlBean> getMenuUrlList() {
		return menuRepository.getMenuUrlList();
	}

	@Override
	public List<MenuUserMappingBean> getMenuMappingList(Integer userId) {
		return menuMappingRepository.getMenuMappingList(userId);
	}

	@Override
	public void saveAllSelected(@Valid int[] permissionIds) {
		List<Integer> permIds = new ArrayList<>();
		int count=0;
		int userId = permissionIds[permissionIds.length-1];
		menuMappingRepository.deleteAllPermissionByUserId(userId);
		//System.out.println(userId);
		if(permissionIds.length>1) {
		for (int id : permissionIds) {
			   ++count;
			  //System.out.println(id);
			  MenuUserMappingBean mappingBean = new MenuUserMappingBean();
			  mappingBean.setMenuId(id);
			  mappingBean.setUserId(userId);
			  menuMappingRepository.save(mappingBean);
			  if(count==permissionIds.length-1)
				  break;
		 }
	  }
	}
	
	@Transactional
	public ResponseEntity<String> editUserService(User user) {

	    // Find existing user from database
	    User dbUser = userRepository.findById(user.getId())
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "User Not Found : " + user.getId()));

	    // Update normal User fields
	    dbUser.setName(user.getName());
	    dbUser.setUsername(user.getUsername());
	    dbUser.setEmail(user.getEmail());
	    
	    if(user.getPassword().length()>0) {
	    	dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
	    }
	    
	    dbUser.setEmployeeId(user.getEmployeeId());
	    dbUser.setContactNo(user.getContactNo());
	    dbUser.setDesignation(user.getDesignation());
	    dbUser.setStatus(user.getStatus());
	    dbUser.setIsAllowedCreateProject(user.getIsAllowedCreateProject());

	    // Create project list
	    List<Project> projectList = new ArrayList<>();

	    // Get selected projects
	    if (user.getProject() != null) {

	        for (Project p : user.getProject()) {

	            // Find actual Project entity from database
	            Project project = projectRepo.findById(p.getId())
	                    .orElseThrow(() ->
	                            new RuntimeException(
	                                    "Project Not Found : "
	                                    + p.getId()));

	            projectList.add(project);
	        }
	    }

	    // Set projects to existing User
	    dbUser.setProject(projectList);

	    // Save and force Hibernate to execute SQL
	    userRepository.saveAndFlush(dbUser);

	    System.out.println("Number of projects assigned: "
	            + dbUser.getProject().size());

	    System.out.println("User entry saved successfully");

	    return ResponseEntity.ok(
	            "User's details updated successfully");
	}
	
	public Long countUserService() {
		return userRepository.count();
	}
	
	public List<LoginLogs> getLoginLogsService() {
		List<LoginLogs> log = logsRepo.findAll();
		return log;
	}
	
	@Override
	public List<?> getFilteredLoginLogsService(String name, LocalDate from, LocalDate to) {

	    Date fromDate = null;
	    Date toDate = null;

	    if (from != null) {
	        fromDate = Date.from(
	                from.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    }

	    if (to != null) {
	        toDate = Date.from(
	                to.atTime(LocalTime.MAX)
	                  .atZone(ZoneId.systemDefault())
	                  .toInstant());
	    }

	    return logsRepo.findLoginHistory(name, fromDate, toDate);
	}
	
	public List<Project> findProjectListByUserId(Long id, LocalDate from, LocalDate to, Status status) {
		return userRepository.findFilteredProjects(id, from, to ,status);
	}
	
	public List<?> getListofName() {
		return userRepository.findNameByUsername();
	}
}

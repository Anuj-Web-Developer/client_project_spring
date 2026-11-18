package com.billing.login.message.request;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.billing.project.entity.Project;

public class SignUpForm {
	
	@NotBlank(message="Please Enter Name.")
    @Size(min = 3, max = 50)
    private String name;

//    @NotBlank(message="Please Enter Unique User Name.")
//    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @Size(max = 50)
    @NotBlank(message="Please Enter Email-Id.")
    @Email(message="Please Enter Valid Email-Id.")
    private String email;
    
    @NotBlank(message="Please Enter Employee Id.")
    private String employeeId;
    
    @NotBlank(message="Please Enter Phone Number.")
    private String contactNo;
    
    @NotBlank(message="Please Enter Designation.")
    private String designation;
    
    private int status;
    
    private Set<String> role;
    
    @NotBlank(message="Please Enter password.")
    @Size(min = 6, max = 40)
    private String password;
    
    private List<Project> project;

    private boolean isAllowedCreateProject = false;
    
	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	public boolean getIsAllowedCreateProject() {
		return isAllowedCreateProject;
	}

	public void setIsAllowedCreateProject(boolean isAllowedCreateProject) {
		this.isAllowedCreateProject = isAllowedCreateProject;
	}
    
    
    
}
package com.billing.login.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.billing.project.entity.Project;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Please Enter Name.")
	@Size(min = 2, max = 50, message = "name length should be between 3 to 50")
	private String name;

	@NotBlank(message = "Please Enter Unique User Name.")
	@Size(min = 3, max = 50)
	private String username;

	@Size(max = 50)
	@NotBlank(message = "Please Enter Email-Id.")
	@Email(message = "Please Enter Valid Email-Id.")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "Please Enter password.")
	@Size(min = 6)
	private String password;

	@NotBlank(message = "Please Enter Employee Id.")
	@Column(unique = true)
	private String employeeId;

	@NotBlank(message = "Please Enter Phone Number.")
	private String contactNo;

	@NotBlank(message = "Please Enter Designation.")
	private String designation;

	private int status;

//		@CreatedDate
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	Date createdTime;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	Date modifiedTime;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	Date lastLoginTime;

	@Column(name = "login_ip_address")
	private String loginIpAddress;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Project> project;

	private boolean isAllowedCreateProject = false;

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsAllowedCreateProject() {
		return isAllowedCreateProject;
	}

	public void setIsAllowedCreateProject(boolean isAllowedCreateProject) {
		this.isAllowedCreateProject = isAllowedCreateProject;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
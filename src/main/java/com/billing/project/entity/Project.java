package com.billing.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.billing.attachment.entity.Attachment;
import com.billing.customer.model.CustomerModel;
import com.billing.enumeration.Status;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@NotBlank(message="Project name is required.")
	private String projectName;
	
	private String projectWebsite;
	private String socialMedia;
	private String youtube;
	private String facebook;
	private String instagram;
	private String linkedIn;
	private String x_com;
	private String others;
	
//	@NotBlank(message = "Login url is required")
	private String loginUrl;
	
//	@NotBlank(message="Username is required.")
//	@Size(min=3, max=50, message="Username length minimum 3 to maximum 50 characters.")
	private String username;
	
//	@NotBlank(message="Password is required.")
//	@Length(min=4, message="Password length atleast 4 characters.")
	private String password;
	
	private String serverUrl;
	
	private String serverUsername;
	
	private String serverPassword;
	
//	@NotNull(message = "Project start date is required.")
	private LocalDate projectStartDate;
	
	private LocalDate projectEndDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
//	@NotBlank(message = "Project description is required.")
	private String description;
	
	@OneToOne
	private CustomerModel customer;
	
	@Transient
	private boolean isSendEmail;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
//	@OneToOne
//	private Attachment attachment;
	
	private String loginAttachment;
	private String serverAttachment1;
	private String serverAttachment2;
	
	
	
//	public Attachment getAttachment() {
//		return attachment;
//	}
//
//	public void setAttachment(Attachment attachment) {
//		this.attachment = attachment;
//	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setSendEmail(boolean isSendEmail) {
		this.isSendEmail = isSendEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectWebsite() {
		return projectWebsite;
	}

	public void setProjectWebsite(String projectWebsite) {
		this.projectWebsite = projectWebsite;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getX_com() {
		return x_com;
	}

	public void setX_com(String x_com) {
		this.x_com = x_com;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(LocalDate projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public LocalDate getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(LocalDate projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServerUsername() {
		return serverUsername;
	}

	public void setServerUsername(String serverUsername) {
		this.serverUsername = serverUsername;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}
	
	public boolean getIsSendEmail() {
		return isSendEmail;
	}
	
	public void setIsSendEmail(boolean isSendEmail) {
		this.isSendEmail = isSendEmail;
	}

	public String getLoginAttachment() {
		return loginAttachment;
	}

	public void setLoginAttachment(String loginAttachment) {
		this.loginAttachment = loginAttachment;
	}

	public String getServerAttachment1() {
		return serverAttachment1;
	}

	public void setServerAttachment1(String serverAttachment1) {
		this.serverAttachment1 = serverAttachment1;
	}

	public String getServerAttachment2() {
		return serverAttachment2;
	}

	public void setServerAttachment2(String serverAttachment2) {
		this.serverAttachment2 = serverAttachment2;
	}
	
	
}


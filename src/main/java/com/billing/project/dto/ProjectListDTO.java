package com.billing.project.dto;

import com.billing.enumeration.Status;


public class ProjectListDTO {

	private Integer projectId;
	private String projectName;
	private String customerName;
	private Status status;
	private String companyName;
	
	public ProjectListDTO(
	        Integer id,
	        String projectName,
	        Status status,
	        String customerName,
	        String companyName) {

	    this.projectId = id;
	    this.projectName = projectName;
	    this.status = status;
	    this.customerName = customerName;
	    this.companyName = companyName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}

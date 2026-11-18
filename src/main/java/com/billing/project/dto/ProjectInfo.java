package com.billing.project.dto;

import java.time.LocalDate;
import com.billing.enumeration.Status;

public class ProjectInfo {

	private Integer id;
    private String projectName;
    private String projectWebsite;
    private LocalDate projectStardDate;
	private LocalDate projectEndDate;
    private Status status;
    private String customerName;

    public ProjectInfo(Integer id, String projectName, String projectWebsite, LocalDate projectStardDate,
            LocalDate projectEndDate, Status status, String customerName) {
    	this.id = id;
        this.projectName = projectName;
        this.projectWebsite = projectWebsite;
        this.projectStardDate = projectStardDate;
        this.projectEndDate = projectEndDate;
        this.status = status;
        this.customerName = customerName;
    }
    
    public Integer getId() {
		return id;
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
    public LocalDate getProjectStardDate() {
        return projectStardDate;
    }
    public void setProjectStardDate(LocalDate projectStardDate) {
        this.projectStardDate = projectStardDate;
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
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
package com.billing.logMgmt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="loginlogs")
public class LoginLogs {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;

	@Column(name="login_user_name")
	String loginUserName;
	
	@Column(name="login_logs")
	String loginLogs;
	
	@Column(name="role")
	int role;
	
	@Column(name="user_id")
	long userId;
	
	@Column(name="login_ip_address")
	String loginIpAddress;
	
	@Column(name="browser_name")
	String browserName;
	
	@Column(name="host_name")
	String hostName;
	
	
	@Column(name="os_name")
	String osName;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	Date loginTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginLogs() {
		return loginLogs;
	}

	public void setLoginLogs(String loginLogs) {
		this.loginLogs = loginLogs;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	

	}

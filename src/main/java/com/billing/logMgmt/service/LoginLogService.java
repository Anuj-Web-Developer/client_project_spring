package com.billing.logMgmt.service;

import java.util.List;

import com.billing.logMgmt.model.LoginLogs;

public interface LoginLogService {
	
	public List<LoginLogs> getLoginLogsList();
	
	public void deleteAllSelected(int[] logsIds);
	
	public List<LoginLogs> getUserLogs(Long userId);

} 

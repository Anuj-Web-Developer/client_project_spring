package com.billing.logMgmt.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.logMgmt.model.LoginLogs;
import com.billing.logMgmt.repository.LoginLogRepository;
import com.billing.logMgmt.service.LoginLogService;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	
	@Autowired
	LoginLogRepository logsRepository;
	
	
	@Override
	public List<LoginLogs> getLoginLogsList() {
		return logsRepository.findAllByOrderByIdDsc();
	}

	
	@Override
	public void deleteAllSelected(@Valid int logIds[]) {
		List<Integer> loginLogIds = Arrays.stream(logIds).boxed().collect(Collectors.toList());
		logsRepository.deleteByIncludedId(loginLogIds);
	}


	@Override
	public List<LoginLogs> getUserLogs(Long userId) {
		return logsRepository.getUserLogsByUserId(userId);
	}

	


	


}

package com.billing.logMgmt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.common.CommonUtils;
import com.billing.common.ExcelUtil;
import com.billing.exception.ResourceNotFoundException;
import com.billing.logMgmt.model.LoginLogs;
import com.billing.logMgmt.service.LoginLogService;
import com.billing.login.model.User;
import com.billing.login.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/login-logs")
public class LoginLogController {
	
					@Autowired
					LoginLogService  loginLogService;
					
					@Autowired
					UserService  userService;
					
					@Autowired
					ServletContext context;
					
					@Autowired
					HttpServletRequest  request;
					
					@Autowired
					HttpServletResponse response;
				
					@GetMapping("/loginLogList")
					public List<LoginLogs> getLoginLogsList(){
					    return loginLogService.getLoginLogsList();
					}
				
					
					@PostMapping("/deleteLoginLogs")
					public Map<String, Boolean> deleteLoginLogs(@Valid @RequestBody int logIds[]) {
						loginLogService.deleteAllSelected(logIds);
						Map<String, Boolean> response = new HashMap<>();
						response.put("deleted", Boolean.TRUE);
						return response;
					}
					
					
					@GetMapping("/userList")
					public List<User> getUserList(){
						 return userService.getUserListForlogs();
					}
					
					
					
					//****** Login Logs ********//	
					@GetMapping("/getUserLogs/{id}")
					public List<LoginLogs> getUserLogs(@PathVariable(value = "id") Long userId)throws ResourceNotFoundException, IOException {
						List<LoginLogs> logsList = loginLogService.getUserLogs(userId);
						User user = userService.getById(userId);
					    List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
						   if(logsList.size()>0 && logsList!=null)
							  { 
								for (LoginLogs logBean : logsList)
							        { 
									  Map<String, Object> map = new LinkedHashMap<>();
							          map.put("User Name", logBean.getLoginUserName()); 
							          map.put("Login Time", logBean.getLoginTime());
							          map.put("IP", logBean.getLoginIpAddress());
							          map.put("Browser", logBean.getBrowserName());
							          map.put("OS Name", logBean.getOsName());
							          map.put("Host Name", logBean.getHostName());
//							          map.put("Details",  logBean.getLoginLogs());
							          lst.add(map);
							       } 
								     if(lst.size()==0)
							          {
							        	 Map<String, Object> map = new LinkedHashMap<>();
							        	 map.put("User Name", ""); 
								          map.put("Login Time", "");
								          map.put("IP", "");
								          map.put("Browser", "");
								          map.put("OS Name", "");
								          map.put("Host Name","");
//								          map.put("Details",  "");
							             lst.add(map); 
							         } 
							       if(lst.size()>0)
							         { 
							    	   XSSFWorkbook workbookCreate = ExcelUtil.exportExcel(lst, user.getCreatedTime()+"" , user.getModifiedTime()+"", " User Name -"+ user.getName());
							    	   String folderPath = context.getRealPath("/ReportExcel/");
							           File file = new File(folderPath);
							           if(!file.exists()){ file.mkdir(); 
							         } 
							        FileOutputStream outputStream = new  FileOutputStream(new File(folderPath)+"/Login_log_Report.xlsx");
							        workbookCreate.write(outputStream);
							        outputStream.close();
							  } }
						return logsList;
					}
					
					
					@GetMapping(value="/downloadLoginReport" , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
					public void downloadLoginReport()  {
						String folderPath = context.getRealPath("/ReportExcel/");
						File file = new File(folderPath + File.separator + "Login_log_Report.xlsx");
						if (file.exists()) {
							ServletOutputStream os = null;
							FileInputStream in = null;
							try {
								os = response.getOutputStream();
								in = new FileInputStream(file);
								response.setContentType("application/vnd.ms-excel");
								response.setHeader("Content-disposition", "attachment;filename="+"Login_log_Report.xlsx");
								byte[] buffer = new byte[1024];
								int length = 0;
								while ((length = in.read(buffer)) != -1) {
									os.write(buffer, 0, length);
								}
							} catch (IOException io) {
								io.printStackTrace();
							} finally {
								CommonUtils.closeInputStream(in);
								CommonUtils.closeOutputStream(os);
							}
						}
							
					}
					
}

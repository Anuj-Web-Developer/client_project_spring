package com.billing.backup;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeTestController {
	
	
		@Autowired
		HttpServletRequest request;
		
		@Autowired
		HttpServletResponse response;
	
		@Autowired
	  	ServletContext context;
		
		@Autowired
		JavaMailSender mailSender;
		
	 @GetMapping("/testAPI")
     public String testAPI() {
		System.out.println("This is working !");
		return "This is GetMapping working !!" ;
     }
	
	 @PostMapping("/postAPI")
     public String testPostAPI() {
		System.out.println("This is working !");
		return "This is PostMapping working !!" ;
     }
	 
	 @PutMapping("/putAPI")
     public String testPutAPI() {
		System.out.println("This is working !");
		return "This is PutMapping working !!" ;
     }
	
	


}

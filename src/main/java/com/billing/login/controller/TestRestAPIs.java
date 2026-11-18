package com.billing.login.controller;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
@RestController
public class TestRestAPIs {
	
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Project Management Board";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	@GetMapping("/ap")
	public String lkccess1() throws UnsupportedEncodingException {
		
		/*
		 * String[] pieces =
		 * "$10$a7AN2.deB6W7z525EXVjGe/CfFvBx8GW2zkZ2btE8YmMuDOq7HQIi".split("\\.");
		 * String b64payload = pieces[1]; String jsonString = new
		 * String(Base64.decodeBase64(b64payload), "UTF-8");
		 * System.out.println(jsonString);
		 */
		 
		return ">>> This is Test Content ";
	}
}
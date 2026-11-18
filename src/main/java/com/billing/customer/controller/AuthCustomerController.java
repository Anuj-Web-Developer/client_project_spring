package com.billing.customer.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.customer.model.CustomerModel;
import com.billing.customer.repository.CustomerRepository;
import com.billing.customer.service.CustomerService;
import com.billing.customer.serviceImpl.CustomerServiceImpl;
import com.billing.exception.ResourceNotFoundException;
import com.billing.login.security.WebSecurityConfig;
import com.billing.otp.service.OtpService;
import com.billing.project.entity.Project;
import com.billing.project.repository.ProjectRepository;
import com.stripe.exception.StripeException;

@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/auth/customer")
public class AuthCustomerController {

	@Autowired
	private OtpService otpServ;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	// Send OTP to Customer
	@PostMapping("/send-otp/{email}")
	public ResponseEntity<?> customerSendOTPController(@PathVariable String email) {

		Optional<CustomerModel> customer = customerRepo.findByEmail(email);
		String name = customer.get().getName();
		if (customer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email is not present");
		}
		
		String sms = "Your One Time Password (OTP) for login is below";
		
		String response = (String) otpServ.sendOTP(email, name, sms).getBody();
		return ResponseEntity.ok().body(response);
	}
	
	

	// Verify OTP provided by Customer
	@PostMapping("/verify-otp/{email}/{otp}")
	public ResponseEntity<?> customerVerifyOTPController(@PathVariable String email, @PathVariable Integer otp,
			HttpServletRequest request, HttpServletResponse response) {

		ResponseEntity<?> myResponse = otpServ.verifyOtp(email, otp);

		if (myResponse.getStatusCodeValue() != 202) {
			return myResponse;
		}

		CustomerModel customer = customerRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null,
				List.of(new SimpleGrantedAuthority("ROLE_USER")));

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);

		HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();

		repository.saveContext(context, request, response);
		return ResponseEntity.ok("OTP verified successfully");
	}

//	************************* GET CUSTOMER PROFILE ******************************************

	@GetMapping("/getProfile")
	public ResponseEntity<?> getCustomerProfile(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

		Authentication auth = securityContext.getAuthentication();

		Object principal = auth.getPrincipal();
//		System.out.println(principal);

		return ResponseEntity.ok(principal);
	}

	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<?> getCustomerByIdController(@PathVariable Integer id) {
		CustomerModel customer = customerRepo.findById(id).get();
		return ResponseEntity.ok(customer);
	}

	@GetMapping("/getProjectList/{customerId}")
	public ResponseEntity<?> getProject(@PathVariable Integer customerId) {
		List<Project> project = projectRepo.findByCustomerId(customerId);
		return ResponseEntity.ok(project);
	}

	@GetMapping("/getProject/{projectId}")
	public ResponseEntity<?> getProjectByIdController(@PathVariable Integer projectId) {
		return ResponseEntity.ok(projectRepo.findById(projectId));
	}

//	************************************ UPDATE RPOJECT PASSWORD ****************************************************

	@PostMapping("/updateProject")
	public ResponseEntity<?> updateProjectController(@RequestBody Project project) {
		Project db_project = projectRepo.findById(project.getId()).get();

		db_project.setPassword(project.getPassword());
		db_project.setUsername(project.getUsername());
		projectRepo.save(db_project);
		return ResponseEntity.ok("Password updated successfully");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {

		request.getSession().invalidate();
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("Session destroyed");
	}

}

package com.billing.login.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billing.common.BillingConstants;
import com.billing.common.DateUtil;
import com.billing.common.service.CommonService;
import com.billing.customer.repository.CustomerRepository;
import com.billing.enumeration.Status;
import com.billing.exception.ResourceNotFoundException;
import com.billing.logMgmt.model.LoginLogs;
import com.billing.logMgmt.repository.LoginLogRepository;
import com.billing.login.message.request.LoginForm;
import com.billing.login.message.request.SignUpForm;
import com.billing.login.message.response.JwtResponse;
import com.billing.login.message.response.ResponseMessage;
import com.billing.login.model.NameListDTO;
import com.billing.login.model.Role;
import com.billing.login.model.RoleName;
import com.billing.login.model.User;
import com.billing.login.repository.RoleRepository;
import com.billing.login.repository.UserRepository;
import com.billing.login.security.jwt.JwtProvider;
import com.billing.login.service.UserService;
import com.billing.login.service.serviceImpl.UserServiceImpl;
import com.billing.otp.service.OtpService;
import com.billing.project.entity.Project;
import com.billing.project.repository.ProjectRepository;
import com.billing.urlAccess.bean.MenuUrlBean;
import com.billing.urlAccess.bean.MenuUserMappingBean;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	ServletContext context;

	Map<String, String> errors;

	@Autowired
	CommonService commonService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	LoginLogRepository logsRepository;

	@Autowired
	UserService userService;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	private OtpService otpServ;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws UnknownHostException {

		System.out.println(loginRequest.getUsername() + "-----" + loginRequest.getPassword());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = jwtProvider.generateJwtToken(authentication);
//		System.out.println("Token: "+accessToken);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		User user = userRepository.findByEmailWithActiveStatus(loginRequest.getUsername(), BillingConstants.ACTIVE);

		// ---------- Browser & IP ----------
		final String browserDetails = request.getHeader("User-Agent");
		final String lowerCaseBrowser = browserDetails.toLowerCase();

		String os = commonService.getClientOS(lowerCaseBrowser, browserDetails);
		String browser = commonService.getClientBrowser(lowerCaseBrowser, browserDetails);

		String ip = InetAddress.getLocalHost().getHostAddress();
		String hostName = InetAddress.getLocalHost().getHostName();

		// ---------- Login Logs ----------
		LoginLogs logs = new LoginLogs();
		logs.setId(0);
		logs.setLoginLogs(browserDetails);
		logs.setLoginTime(DateUtil.getCurrentDate());
		logs.setLoginIpAddress(ip);
		logs.setOsName(os);
		logs.setBrowserName(browser);
		logs.setHostName(hostName);

		Object loginUser;

//		Set<Role> roles = user.getRoles();
		
		logs.setLoginUserName(user.getName());
//		logs.setRole(user.getRoles());
		logs.setUserId(user.getId());

		logsRepository.save(logs);

		user.setLoginIpAddress(ip);
		user.setLastLoginTime(DateUtil.getCurrentDate());

		loginUser = userRepository.save(user);

//			throw new UsernameNotFoundException("User not found");

		System.out.println("Principal Class : " + userDetails.getClass().getName());
		System.out.println("Authorities : " + userDetails.getAuthorities());
		return ResponseEntity
				.ok(new JwtResponse(accessToken, userDetails.getUsername(), userDetails.getAuthorities(), loginUser));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addProfile")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		
		Map<String, String> errors = new HashMap<>();

		// Check email
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			errors.put("email", "Email is already in use!");
		}

		// Generate username from email
		String username = signUpRequest.getEmail().substring(0, signUpRequest.getEmail().indexOf("@"));
		int otp = otpServ.generateOtp();
		username = username + otp;
		
		if (userRepository.existsByUsername(username)) {
			errors.put("username", "Username is already taken!");
		}

		// Create User
		User user = new User();

		user.setName(signUpRequest.getName());
		user.setUsername(username);
		user.setEmail(signUpRequest.getEmail());
		user.setEmployeeId(signUpRequest.getEmployeeId());
		user.setContactNo(signUpRequest.getContactNo());
		user.setDesignation(signUpRequest.getDesignation());
		user.setStatus(signUpRequest.getStatus());
		user.setProject(signUpRequest.getProject());
		user.setIsAllowedCreateProject(signUpRequest.getIsAllowedCreateProject());

		// Encrypt password
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		// Assign Role
		Set<Role> roles = new HashSet<>();

		Role adminRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: User Role not found."));
		roles.add(adminRole);

		user.setRoles(roles);

		// Save User
		userRepository.save(user);

		return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));
	}

	// ************************* User List *****************//

	@GetMapping("/userList")
	public List<User> getUserList() {
		return userService.getUserList();
	}

	// ************************* Find user by Id *****************//

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {

		try {
			return ResponseEntity.ok(userService.getById(id));
		} catch (ResourceNotFoundException e) {

//					e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	// ********** User Edit *******************//
	@GetMapping("/userEdit/{id}")
	public ResponseEntity<User> getCustomerById(@PathVariable(value = "id") Integer userId)
			throws ResourceNotFoundException {
		User user = userService.getById(userId);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping(value = "/images/{name}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable(value = "name") String name) throws IOException {
		File file = new File(context.getRealPath("/user_pics/") + File.separator + name);
		return ResponseEntity.ok().contentType(commonService.contentType(name))
				.body(new InputStreamResource(new FileInputStream(file)));
	}

	// ********** User's send OTP *******************//
	@GetMapping("/sendUserOTP/{email}")
	public ResponseEntity<?> SendOtpUser(@PathVariable String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		String name = user.getName();
		String sms = "Your OTP for password reset is below. It is valid for 2 minutes. Do not share this OTP with anyone.";
		return otpServ.sendOTP(email, name, sms);
	}

	// ********** Change User's Password *******************//

	@PostMapping("/changePassword/{email}")
	public ResponseEntity<User> updateEmployee(@PathVariable String email, @RequestBody User user)
			throws ResourceNotFoundException {
		
		User userB = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
		userB.setPassword(encoder.encode(user.getPassword()));
		User userBean = userRepository.save(userB);
		return ResponseEntity.ok(userBean);
	}

	// ********** Update User' Details *******************//

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUserController(@Valid @RequestBody User user) {
		
		return userService.editUserService(user);
	}

	@PostMapping("/lastLoginInfo")
	public ResponseEntity<LoginLogs> updateEmployee(@Valid @RequestBody int id) {
		LoginLogs logs = logsRepository.findSecondLast(id).get(1);
		System.out.println(logs.getId());
		return ResponseEntity.ok(logs);
	}

	@PostMapping("/savePermission")
	public Map<String, Boolean> savePermissions(@Valid @RequestBody int permissionIds[]) {
		userService.saveAllSelected(permissionIds);
		// System.out.println("UserId-"+permissionIds.length);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Save", Boolean.TRUE);
		return response;
	}

	@GetMapping("/countUser")
	public Long countUserController() {
		return userService.countUserService();
	}

	@GetMapping("/loginLogs")
	public ResponseEntity<?> loginLogsController() {
		return ResponseEntity.ok(userService.getLoginLogsService());
	}
	
	@GetMapping("/filteredLogs")
	public ResponseEntity<?> getFilteredLoginLogsController(@RequestParam(required = false) String name,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
		
		return ResponseEntity.ok(userService.getFilteredLoginLogsService(name, from, to));
	}
	
//	********************************** GET PROJECT LIST USING USER'S ID *******************************************************************************
	
	@GetMapping("/filteredProjects")
	public ResponseEntity<?> getFilteredProjects(
	        @RequestParam("userId") Long userId,

	        @RequestParam(value = "from", required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	        LocalDate from,

	        @RequestParam(value = "to", required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	        LocalDate to,

	        @RequestParam(value = "status", required = false)
	        Status status) {

	    try {

	        List<Project> projects =
	        		userService.findProjectListByUserId(
	                        userId,
	                        from,
	                        to,
	                        status
	                );

	        return ResponseEntity.ok(projects);

	    } catch (Exception e) {

	        e.printStackTrace();

	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Unable to fetch project report");
	    }
	}
	
	@GetMapping("/listOfName")
	public ResponseEntity<?> getListOfNameController(Authentication auth) {
		
		String role = auth.getAuthorities()
	            .stream()
	            .findFirst()
	            .map(GrantedAuthority::getAuthority)
	            .orElse(null);
		if(role.equals("ROLE_ADMIN")) {
			return ResponseEntity.ok(userService.getListofName());
		}else {
			String username = auth.getName();
			return ResponseEntity.ok(userRepository.findNameByUsername(username));
		}
		
	}
	
	@GetMapping("/getRole")
	public ResponseEntity<?> getRoleController(Authentication auth) {
		String role = auth.getAuthorities()
	            .stream()
	            .findFirst()
	            .map(GrantedAuthority::getAuthority)
	            .orElse(null);
		return ResponseEntity.ok(role);	
		}
	
//	************************************ Change logged in user's Password ******************************************************
	
	@PostMapping("/updatePassword")
	public ResponseEntity<?> changePassword(@RequestBody User user, Authentication auth) {
		
		String username = auth.getName();
		
		System.out.println("Logged in Username is: "+username);
		System.out.println("Received Password is: "+user.getPassword());
		
		 User db_user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		 db_user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userRepository.save(db_user);
		return ResponseEntity.ok("Password updated successfully");
	}
	
}
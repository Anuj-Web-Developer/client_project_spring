package com.billing.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.billing.otp.service.OtpService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
		
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerModel customer) {
		return customerServ.saveCustomerService(customer);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerModel>> getAllCustomers() {
		return customerServ.getAllCustomersService();
	}
	
	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable int id) {
		return customerServ.getCustomerByIdService(id);
	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<?> editCustomerController(@Valid @RequestBody CustomerModel customer) {
		return customerServ.updateCustomerService(customer);
	}
	
	@GetMapping("/findWithKeyword/{keywords}")
	public List<CustomerModel> findNameContainsKeywordsController(@PathVariable String keywords) {
		return customerServ.findNameContainsKeywords(keywords);
	}
	
	@GetMapping("/countCustomer")
	public Long countCustomerController() {
		return customerServ.countCustomerService();
	}
}

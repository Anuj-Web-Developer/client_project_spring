package com.billing.customer.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.billing.customer.model.CustomerModel;



public interface CustomerService {

	// Save new customer
	 ResponseEntity<?> saveCustomerService(CustomerModel customer);
	
	// Get All Customers
	 ResponseEntity<List<CustomerModel>> getAllCustomersService();
	 
	 // Get Customer By ID
	 ResponseEntity<?> getCustomerByIdService(int id);
	 
	 // Update Customer detail
	 ResponseEntity<?> updateCustomerService(CustomerModel customer);
	
	 // Find list of customer contains keywords
	 List<CustomerModel> findNameContainsKeywords(String keyword);
	 
	 Long countCustomerService();
	 
	 
}

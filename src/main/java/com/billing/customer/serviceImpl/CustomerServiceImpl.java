package com.billing.customer.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.billing.customer.model.CustomerModel;
import com.billing.customer.repository.CustomerRepository;
import com.billing.customer.service.CustomerService;
import com.billing.exception.ResourceNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	// Save new customer
	@Override
	public ResponseEntity<?> saveCustomerService(CustomerModel customer) {

		customerRepo.save(customer);
		return ResponseEntity.ok("Customer saved successfully");
	}

	// Get All Customers
	@Override
	public ResponseEntity<List<CustomerModel>> getAllCustomersService() {
		List<CustomerModel> customers = customerRepo.findAllByOrderByIdDsc();
		return ResponseEntity.ok(customers);
	}

	// Get Customer By Id
	@Override
	public ResponseEntity<?> getCustomerByIdService(int id) {

		Optional<CustomerModel> customer = customerRepo.findById(id);

		
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}
	}

	// Update Customer Detail
	public ResponseEntity<?> updateCustomerService(CustomerModel customer) {

		customer.setId(customer.getId());
		if (customer.getId() > 0) {

			customerRepo.save(customer);
			return ResponseEntity.ok("Customer updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Id not found");
		}
	}

	// Find list of customer contains keywords
	@Override
	public List<CustomerModel> findNameContainsKeywords(String keyword) {
		return customerRepo.searchCustomer(1, keyword);
	}

	public Long countCustomerService() {
		return customerRepo.count();
	}

}

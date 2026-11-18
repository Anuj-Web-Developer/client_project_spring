package com.billing.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.customer.model.CustomerModel;
import com.billing.login.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
	
	@Query("SELECT c FROM CustomerModel c ORDER By c.id DESC")
	List<CustomerModel> findAllByOrderByIdDsc();
	
	Optional<CustomerModel> findByEmail(String email);
	
	@Query("SELECT c FROM CustomerModel c " +
		       "WHERE c.status = :status " +
		       "AND (" +
		       "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "OR LOWER(c.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
		       ")")
	List<CustomerModel> searchCustomer(
			@Param("status") int status,
			@Param("keyword") String keyword);
	
}

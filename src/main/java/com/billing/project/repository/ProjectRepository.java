package com.billing.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.billing.customer.model.CustomerModel;
import com.billing.enumeration.Status;
import com.billing.project.dto.ProjectInfo;
import com.billing.project.dto.ProjectListDTO;
import com.billing.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("SELECT p FROM Project p ORDER By p.id DESC")
	List<Project> findAllByOrderByIdDsc();
	
	List<Project> findByCustomerId(int customerId);
		
	@Query("SELECT p " +
		       "FROM Project p " +
		       "WHERE (:from IS NULL OR p.projectStartDate >= :from) " +
		       "AND (:to IS NULL OR p.projectEndDate <= :to) " +
		       "AND (:status IS NULL OR p.status = :status)")
		List<Project> findFilteredProjects(
		        @Param("from") LocalDate from,
		        @Param("to") LocalDate to,
		        @Param("status") Status status);
	
	
}
 

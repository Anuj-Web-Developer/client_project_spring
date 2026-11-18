package com.billing.login.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.enumeration.Status;
import com.billing.login.model.NameListDTO;
import com.billing.login.model.User;
import com.billing.project.entity.Project;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    
    
    @Query("SELECT c FROM User c ORDER By c.id DESC")
	List<User> findAllByOrderByIdDsc();
    
    @Query("SELECT c FROM User c where c.email=:username and c.status=:active")
	User findByEmailWithActiveStatus(String username, int active);

	 @Transactional
	 @Modifying
	 @Query("UPDATE  User  c  set c.name=:name , c.contactNo=:contactNo , c.employeeId=:employeeId , c.designation=:designation ,  c.status= :status , c.modifiedTime = :currentDate where c.id = :id")
     int updateUserProfile(Long id, String name, String contactNo, String employeeId, String designation, int status, Date currentDate);
	 
	 @Query("SELECT p " +
		       "FROM User u " +
		       "JOIN u.project p " +
		       "WHERE u.id = :userId " +
		       "AND (:from IS NULL OR p.projectStartDate >= :from) " +
		       "AND (:to IS NULL OR p.projectEndDate <= :to) " +
		       "AND (:status IS NULL OR p.status = :status)")
		List<Project> findFilteredProjects(
		        @Param("userId") Long userId,
		        @Param("from") LocalDate from,
		        @Param("to") LocalDate to,
		        @Param("status") Status status);
	 
	 
	 @Query("SELECT new com.billing.login.model.NameListDTO(u.name, u.id) FROM User u")
	 List<NameListDTO> findNameByUsername();
	 
	 
	 @Query("SELECT new com.billing.login.model.NameListDTO(u.name, u.id) " +
		       "FROM User u " +
		       "WHERE u.username = :username")
		NameListDTO findNameByUsername(@Param("username") String username);
}
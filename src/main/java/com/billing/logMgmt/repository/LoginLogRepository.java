package com.billing.logMgmt.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.logMgmt.model.LoginLogs;


@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogs, Integer> {

	    @Modifying
	    @Transactional
	    @Query("delete from LoginLogs c where c.id in (:idList)")
	    public void deleteByIncludedId(@Param("idList") List<Integer> idList);

	    @Query("SELECT l FROM LoginLogs l ORDER BY l.id DESC")
		public List<LoginLogs> findAllByOrderByIdDsc();

		@Modifying
	    @Transactional
	    @Query("SELECT l from LoginLogs l where l.userId = (:userId) ORDER BY l.id DESC ")
		public List<LoginLogs> findSecondLast(@Param("userId") long userId);

		@Query("SELECT l FROM LoginLogs l where  l.userId =:userId ORDER BY l.id DESC")
		public List<LoginLogs> getUserLogsByUserId(Long userId);

		@Query("SELECT l FROM LoginLogs l " +
			       "WHERE (:name IS NULL OR l.loginUserName = :name) " +
			       "AND (:from IS NULL OR l.loginTime >= :from) " +
			       "AND (:to IS NULL OR l.loginTime <= :to) " +
			       "ORDER BY l.loginTime DESC")
			List<LoginLogs> findLoginHistory(
			        @Param("name") String name,
			        @Param("from") Date from,
			        @Param("to") Date to);
	  }

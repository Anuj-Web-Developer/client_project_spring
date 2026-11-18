package com.billing.otp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.otp.model.OTP;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Integer> {

	void deleteAllByEmail(String email);
	
	Optional<OTP> findByEmail(String email);
}

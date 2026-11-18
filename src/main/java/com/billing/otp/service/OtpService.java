package com.billing.otp.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billing.customer.repository.CustomerRepository;
import com.billing.otp.model.OTP;
import com.billing.otp.repository.OtpRepository;

@Service
@Transactional
public class OtpService {

	@Autowired
	private OtpRepository otpRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	// Generate OTP
	public Integer generateOtp() {

		Random random = new Random();
		return 100000 + random.nextInt(900000);
	}

	// Send OTP
	public ResponseEntity<?> sendOTP(String email, String name, String sms) {
		
		Integer otpNum = generateOtp();
//		Integer otpNum =123456;
		
		otpRepo.deleteAllByEmail(email);
		
		OTP otp = new OTP();
	
		otp.setEmail(email);
		otp.setOtp(otpNum);
		otp.setExpiryTime(LocalDateTime.now().plusMinutes(2));
		otpRepo.save(otp);
	
        String otpSendStrFlag=emailService.sendOtp(email, otpNum, name, sms);
       // String otpSendStrFlag="OTP send successfully";
		return ResponseEntity.ok(otpSendStrFlag);
	}
	
	// Verify OTP
		public ResponseEntity<?> verifyOtp(String email, Integer otp) {
			
			Optional<OTP> otpOpt = otpRepo.findByEmail(email);
			if (otpOpt.isEmpty()) {
			    return ResponseEntity.status(HttpStatus.NOT_FOUND)
			            .body("OTP not found");
			}
			OTP oldOtp = otpOpt.get();
			if (LocalDateTime.now().isAfter(oldOtp.getExpiryTime())) {
				return ResponseEntity.status(HttpStatus.GONE).body("OTP has expired");
			}
			if (!oldOtp.getOtp().equals(otp)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong OTP");
			}
			otpRepo.delete(oldOtp);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("OTP verified successfully");
		}

}
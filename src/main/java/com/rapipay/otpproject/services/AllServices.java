package com.rapipay.otpproject.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rapipay.otpproject.dao.EmailOtp;
import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.exception.InvalidEmailException;
import com.rapipay.otpproject.exception.InvalidOTPException;
import com.rapipay.otpproject.exception.OTPExpireException;
import com.rapipay.otpproject.exception.ResourseNotFoundException;

public interface AllServices {
	
	 List<Email> getAllEmail();
	
	 Email getByEmail(String email) throws ResourseNotFoundException ;
	 

		ResponseEntity<String> addEmail(Email email) throws InvalidEmailException ;

		ResponseEntity<String> OtpValidate(Email eo) throws ResourseNotFoundException, OTPExpireException, InvalidOTPException;
	 
	 
	 
	 
	
	

}


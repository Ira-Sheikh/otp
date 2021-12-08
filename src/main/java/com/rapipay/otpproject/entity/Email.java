package com.rapipay.otpproject.entity;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="email")
public class Email{
	@Id
	String email;

	LocalDateTime generatedTime;
	LocalDateTime expiryTime;
	int otp;

	public Email(String email, int otp, int id) {
		super();
		this.email = email;
		this.generatedTime=null;
		this.expiryTime=null;
		this.otp = otp;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime() {
		this.generatedTime = LocalDateTime.now();
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime() {
		this.expiryTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp() {
		
	    this.otp =generateOtp();
	}
	public Email() {
		super();
		
	} 
  
   
	public int generateOtp() {
		
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return number;
	}
}

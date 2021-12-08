package com.rapipay.otpproject.dao;

public class EmailOtp {

	private String email;
	private int otp;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public EmailOtp(String email, int otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public EmailOtp() {
		super();
	}

}
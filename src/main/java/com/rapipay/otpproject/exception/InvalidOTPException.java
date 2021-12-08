package com.rapipay.otpproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidOTPException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidOTPException(String errorMessage)
	{
		super(errorMessage);
	}

}

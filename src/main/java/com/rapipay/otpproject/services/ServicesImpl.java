package com.rapipay.otpproject.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.rapipay.otpproject.entity.Email;
import com.rapipay.otpproject.exception.InvalidEmailException;
import com.rapipay.otpproject.exception.InvalidOTPException;
import com.rapipay.otpproject.exception.OTPExpireException;
import com.rapipay.otpproject.exception.ResourseNotFoundException;
import com.rapipay.otpproject.dao.EmailDao;
import com.rapipay.otpproject.dao.EmailOtp;

@Service
public class ServicesImpl implements AllServices {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailDao emaildao;

	@Override
	public List<Email> getAllEmail() {

		return emaildao.findAll();
	}
@Async
	@Override
	public Email getByEmail(String email) throws ResourseNotFoundException {

		return emaildao.findById(email).orElseThrow(() -> new ResourseNotFoundException("Email not Found"));
	}
@Async
	@Override
	public ResponseEntity<String> addEmail(Email email) throws InvalidEmailException {
		Email x = emaildao.save(email);
		sendEmail(email.getEmail(), email.getOtp());
		if (x != null) {
			return new ResponseEntity<String>("OTP Sent",HttpStatus.OK);
		} else {
			throw new InvalidEmailException("Enter valid Email");
		}
	}
@Async
	void sendEmail(String email, int otp) {
		SimpleMailMessage messsage = new SimpleMailMessage();
		messsage.setTo(email);
		messsage.setSubject("OTP from RAPIPAY");
		messsage.setText("Enter OTP: " + otp + ". The OTP will expire in 10 minutes");
		javaMailSender.send(messsage);
	}

	@Override
	@Async
	public ResponseEntity<String> OtpValidate(Email eo)
			throws ResourseNotFoundException, OTPExpireException, InvalidOTPException {

		Email emailData = getByEmail(eo.getEmail());
		if (emailData == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			if (emailData.getOtp() == (eo.getOtp())) {
				LocalDateTime startTime = emailData.getGeneratedTime();
				LocalDateTime endTime = emailData.getExpiryTime();
				LocalDateTime currentTime = LocalDateTime.now();
				System.out.println(ChronoUnit.MINUTES.between(startTime, currentTime));
				System.out.println(ChronoUnit.MINUTES.between(currentTime, endTime));
				if (ChronoUnit.MINUTES.between(startTime, currentTime) <= 1
						&& ChronoUnit.MINUTES.between(currentTime, endTime) >= 0) {
					return new ResponseEntity<String>("OTP Valid",HttpStatus.OK);
				}
				throw new OTPExpireException("OTP Expired");
			}
			throw new InvalidOTPException("Invalid OTP");
		}
	}
}

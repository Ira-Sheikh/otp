package com.rapipay.otpproject.dao;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rapipay.otpproject.entity.Email;


public interface EmailDao extends MongoRepository<Email, String>{

}

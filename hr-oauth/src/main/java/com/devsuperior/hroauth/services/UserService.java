package com.devsuperior.hroauth.services;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public Serializable findByEmail(String email) {
		System.out.println("Chegou aqui");
		Serializable user = userFeignClient.findByEmail(email).getBody();
		System.out.println("chegou aqui");
		if(user == null) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		
		logger.info("Email found: " + email);
		
		return user;
		
	}
	
}

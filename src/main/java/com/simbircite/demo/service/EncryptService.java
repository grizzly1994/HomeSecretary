package com.simbircite.demo.service;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.simbircite.demo.model.User;

public class EncryptService {

	public void encrypt(User user) {
		String password = user.getPassword();
		String salt = user.getSalt();
		password = new ShaPasswordEncoder().encodePassword(password, salt);
		user.setPassword(password);
	}
}

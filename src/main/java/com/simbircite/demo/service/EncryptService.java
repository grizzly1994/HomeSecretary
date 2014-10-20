package com.simbircite.demo.service;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class EncryptService {

	public String encrypt(String pass, String salt) {
		return new ShaPasswordEncoder().encodePassword(pass, salt);
	}
}

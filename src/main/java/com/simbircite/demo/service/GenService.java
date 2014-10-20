package com.simbircite.demo.service;

import java.util.UUID;

public class GenService {
	
	public String gen() {
		return UUID.randomUUID().toString();
	}
}

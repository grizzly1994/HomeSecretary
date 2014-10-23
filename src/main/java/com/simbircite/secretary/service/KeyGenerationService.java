package com.simbircite.secretary.service;

import java.util.UUID;

public class KeyGenerationService {
	
	public String gen() {
		return UUID.randomUUID().toString();
	}
}

package com.simbircite.secretary.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Restorable {

	@NotNull(message = "{validation.empty}")
    @NotBlank(message = "{validation.empty}")
    @Email(message = "{validation.email}")
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String value) {
		username = value;
	}
}

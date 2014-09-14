package com.simbircite.demo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simbircite.homesecretary.entity.Users;

public class UsersValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(Users.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "birthday", "required");
		
		Users user = (Users)object; 
		if (user.getName().length() < 4) {
			errors.rejectValue("name", "name.short", "Name value is too short");
		}
		
		if (user.getPassword().length() < 6) {
			errors.rejectValue("password", "password.length", "Password must be longer 5 symbols");
		}
		
		//TODO: добавить проверку на слишком старые даты
		if (user.getBirthday().isAfterNow() 
				|| user.getBirthday().isBefore(new DateTime().minusYears(100))) {
			errors.rejectValue("birthday", "birthday.incorrect", "Birthday value is incorrect");
		}
		
		if (!checkEmail(user.getEmail())) {
			errors.rejectValue("email", "email.incorrect", "Email value is incorrect");
		}
	}
	
	private boolean checkEmail(String email) {
		 Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher matcher = pattern.matcher(email);
		 return matcher.matches();
	}

}

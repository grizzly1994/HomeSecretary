package com.simbircite.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simbircite.homesecretary.entity.Category;

public class CategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(Category.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name", "required");
		
		Category category = (Category)object;
		
		if (category.getName().length() < 3) {
			errors.rejectValue("name", "name.short", "name value is too short");
		}
	}

}

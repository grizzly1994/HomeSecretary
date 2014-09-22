package com.simbircite.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simbircite.demo.model.Debt;

public class PeriodicTransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(Debt.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "user", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accrual", "accrual", "required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summ", "summ", "required");
	}
}

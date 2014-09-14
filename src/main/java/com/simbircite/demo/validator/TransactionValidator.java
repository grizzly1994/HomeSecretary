package com.simbircite.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simbircite.homesecretary.entity.Transaction;

public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(Transaction.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "user", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "date", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summ", "summ", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "category", "required");	
		
		Transaction transaction = (Transaction)object;
		
		if (transaction.getDate().isAfterNow()) {
			errors.rejectValue("date", "date.incorrect", "date can't be after today");
		}		
	}
}

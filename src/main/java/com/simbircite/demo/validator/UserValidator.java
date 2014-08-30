package com.simbircite.demo.validator;

import com.simbircite.demo.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор модели данных User
 */
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email" , "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", "birth" , "required");
    }
    
}

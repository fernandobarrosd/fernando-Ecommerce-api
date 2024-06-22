package com.fernando.fernando_ecommerce_api.validations.constraints;

import com.fernando.fernando_ecommerce_api.validations.annotations.Name;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<Name, String>{
    @Override
    public boolean isValid(String nameValue, ConstraintValidatorContext context) {
        if (nameValue != null) {
            String namePattern = "[A-Z][a-zA-Zâãáíé]+(\\s[a-zA-Zâãáíé][a-z]+)*";
            return nameValue.matches(namePattern);
        }
        return true;
    }   
}
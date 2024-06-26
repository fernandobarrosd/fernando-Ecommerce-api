package com.fernando.fernando_ecommerce_api.validations.constraints;

import com.fernando.fernando_ecommerce_api.validations.annotations.CEP;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CEPConstraintValidator implements ConstraintValidator<CEP, String>{
    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep != null) {
            String cepPattern = "/\\d{5}-?\\d{3}/";
            return cep.matches(cepPattern);
        }
        return true;
    }
}
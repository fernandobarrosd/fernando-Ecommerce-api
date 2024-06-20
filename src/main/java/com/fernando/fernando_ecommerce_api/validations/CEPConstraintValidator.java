package com.fernando.fernando_ecommerce_api.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CEPConstraintValidator implements ConstraintValidator<CEP, String>{

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        String cepPattern = "/\\d{5}-?\\d{3}/";
        return cep.matches(cepPattern);
    }
}
package com.fernando.fernando_ecommerce_api.validations.annotations;

import org.springframework.core.annotation.AliasFor;
import jakarta.validation.constraints.Pattern;

@Pattern(regexp = "[A-Z][a-zA-Zâãáíé]+(\\s[a-zA-Zâãáíé][a-z]+)*")
public @interface Name {
    @AliasFor(annotation = Pattern.class)
    String message();
}
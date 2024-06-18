package com.fernando.fernando_ecommerce_api.exceptions;

public class InvalidJWTTokenException extends RuntimeException {
    public InvalidJWTTokenException(String message) {
        super(message);
    }   
}
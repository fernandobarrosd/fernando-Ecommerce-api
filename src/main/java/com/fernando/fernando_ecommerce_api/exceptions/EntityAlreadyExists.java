package com.fernando.fernando_ecommerce_api.exceptions;

public class EntityAlreadyExists extends RuntimeException {
    public EntityAlreadyExists(String message) {
        super(message);
    }
}
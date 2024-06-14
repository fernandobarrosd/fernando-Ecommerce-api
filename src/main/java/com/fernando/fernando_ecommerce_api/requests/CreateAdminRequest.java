package com.fernando.fernando_ecommerce_api.requests;

import lombok.Getter;

@Getter
public class CreateAdminRequest {
    private String name;
    private String email;
    private String password;
}
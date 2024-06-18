package com.fernando.fernando_ecommerce_api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    CLIENT("ROLE_CLIENT");

    private String value;
}
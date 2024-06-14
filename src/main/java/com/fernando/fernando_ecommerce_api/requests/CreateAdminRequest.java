package com.fernando.fernando_ecommerce_api.requests;

import lombok.*;

@Getter
@AllArgsConstructor
public class CreateAdminRequest {
    private String name;
    private String email;
    @Setter
    private String password;
}
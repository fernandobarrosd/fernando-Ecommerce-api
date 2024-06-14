package com.fernando.fernando_ecommerce_api.responses;

import com.fernando.fernando_ecommerce_api.models.Admin;
import lombok.Getter;

@Getter
public class AdminResponse {
    private Integer id;
    private String name;
    private String email;

    public AdminResponse(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.email = admin.getEmail();
    }
}
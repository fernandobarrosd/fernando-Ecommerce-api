package com.fernando.fernando_ecommerce_api.models;

import com.fernando.fernando_ecommerce_api.enums.UserRole;
import com.fernando.fernando_ecommerce_api.requests.AdminRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "admin_table")
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(Integer id, String name, String email, String password) {
        super(id, name, email, password, UserRole.ADMIN);
    }

    public Admin(AdminRequest adminRequest) {
        super(null, adminRequest.getName(), adminRequest.getEmail(), adminRequest.getPassword(), 
        UserRole.ADMIN);
    }
}
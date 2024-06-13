package com.fernando.fernando_ecommerce_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_table")
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(Integer id, String name, String email, String password) {
        super(id, name, email, password);
    }
}
package com.fernando.fernando_ecommerce_api.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client_table")
@Getter
@Setter
public class Client extends User {
    @Column(nullable = false)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client() {
        super();
    }

    public Client(Integer id, String name, String email, String password, String cpf, LocalDateTime birthDate, List<Order> orders) {
        super(id, name, email, password);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.orders = orders;
    }

    
}
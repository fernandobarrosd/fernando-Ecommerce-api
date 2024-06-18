package com.fernando.fernando_ecommerce_api.models;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import com.fernando.fernando_ecommerce_api.enums.UserRole;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;
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
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    private String cep;

    public Client() {
        super();
    }

    public Client(Integer id, String name, String email, String password, String cpf, String cep, LocalDate birthDate, List<Order> orders) {
        super(id, name, email, password, UserRole.CLIENT);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.orders = orders;
        this.cep = cep;
    }

    public Client(ClientRequest clientRequest) {
        super(null, clientRequest.name(), clientRequest.email(), clientRequest.password(), UserRole.CLIENT);
        this.cpf = clientRequest.cpf();
        this.birthDate = clientRequest.birthDate();
        this.orders = new ArrayList<>();
        this.cep = clientRequest.cep();
    }
}
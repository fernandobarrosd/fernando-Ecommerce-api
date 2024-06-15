package com.fernando.fernando_ecommerce_api.responses;

import com.fernando.fernando_ecommerce_api.models.Client;
import lombok.Getter;

@Getter
public class ClientResponse {
    private Integer id;
    private String name;
    private String email;
    private String birthDate;

    public ClientResponse(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.birthDate = client.getBirthDate().toString();
    }
}
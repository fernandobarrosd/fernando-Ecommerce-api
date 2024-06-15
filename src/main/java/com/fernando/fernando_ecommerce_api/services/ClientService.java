package com.fernando.fernando_ecommerce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExists;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.repositories.ClientRepository;
import com.fernando.fernando_ecommerce_api.requests.CreateClientRequest;
import com.fernando.fernando_ecommerce_api.responses.ClientResponse;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientResponse saveClient(CreateClientRequest clientRequest) {
        if (clientRepository.findByEmail(clientRequest.email()).isPresent()) {
            throw new EntityAlreadyExists("The user %s email is already exists".formatted(clientRequest.email()));
        }
        if (clientRepository.findByCpf(clientRequest.cpf()).isPresent()) {
            throw new EntityAlreadyExists("The user %s CPF is already exists".formatted(clientRequest.email()));
        }
        Client client = new Client(clientRequest);
        String passwordEncoded = passwordEncoder.encode(client.getPassword());
        client.setPassword(passwordEncoded);

        clientRepository.save(client);

        return new ClientResponse(client);
    }
}
package com.fernando.fernando_ecommerce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import com.fernando.fernando_ecommerce_api.repositories.ClientRepository;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;
import com.fernando.fernando_ecommerce_api.responses.ClientResponse;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    public ClientResponse saveClient(ClientRequest clientRequest) {
        if (adminRepository.findByEmail(clientRequest.email()).isPresent()) {
            throw new EntityAlreadyExistsException("The user %s email is already exists".formatted(clientRequest.email()));
        }
        if (clientRepository.findByEmail(clientRequest.email()).isPresent()) {
            throw new EntityAlreadyExistsException("The user %s email is already exists".formatted(clientRequest.email()));
        }
        if (clientRepository.findByCpf(clientRequest.cpf()).isPresent()) {
            throw new EntityAlreadyExistsException("The user %s CPF is already exists".formatted(clientRequest.email()));
        }
        Client client = new Client(clientRequest);
        String passwordEncoded = passwordEncoder.encode(client.getPassword());
        client.setPassword(passwordEncoded);

        clientRepository.save(client);

        return new ClientResponse(client);
    }
}
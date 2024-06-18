package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;
import com.fernando.fernando_ecommerce_api.responses.ClientResponse;
import com.fernando.fernando_ecommerce_api.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> registerClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.saveClient(clientRequest);
        return ResponseEntity.created(null).body(clientResponse);
    }
}

package com.fernando.fernando_ecommerce_api.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;

@SpringBootTest
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;
    
    @Test
    public void shouldSaveClientWithSuccess() {
        ClientRequest clientRequest = ClientRequest
        .builder()
        .name("Pedro")
        .email("pedro@test.com")
        .password("pedro123")
        .cpf("000.000.000-00")
        .birthDate(LocalDate.of(1980, 12, 5))
        .build();

        Assertions.assertDoesNotThrow(() -> clientService.saveClient(clientRequest));
    }

    @Test
    public void shouldSaveClientWithEmailAlreadyRegistered() {
        String email = "pedro@test.com";
        ClientRequest clientRequest = ClientRequest
        .builder()
        .name("Pedro")
        .email(email)
        .password("pedro123")
        .cpf("000.000.000-00")
        .birthDate(LocalDate.of(1980, 12, 5))
        .build();

        clientService.saveClient(clientRequest);

        ClientRequest clientRequest2 = ClientRequest
        .builder()
        .name("Pedro")
        .email(email)
        .password("pedro123")
        .cpf("111.111.111-11")
        .birthDate(LocalDate.of(1980, 12, 5))
        .build();

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> clientService.saveClient(clientRequest2));
    }

    @Test
    public void shouldSaveClientWithCpfAlreadyRegistered() {
        String cpf = "000.000.000-00";
        ClientRequest clientRequest = ClientRequest
        .builder()
        .name("Pedro")
        .email("pedro@test.com")
        .password("pedro123")
        .cpf(cpf)
        .birthDate(LocalDate.of(1980, 12, 5))
        .build();

        clientService.saveClient(clientRequest);

        ClientRequest clientRequest2 = ClientRequest
        .builder()
        .name("Pedro")
        .email("pedro2@test.com")
        .password("pedro123")
        .cpf(cpf)
        .birthDate(LocalDate.of(1980, 12, 5))
        .build();

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> clientService.saveClient(clientRequest2));
    }
}
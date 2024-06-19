package com.fernando.fernando_ecommerce_api.requests;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Builder;

@Builder
public record ClientRequest(
    @NotNull(message = "The name is required")
    @NotEmpty(message = "The name not should be empty")
    String name,

    @NotNull(message = "The e-mail is required")
    @Email(message = "The e-mail should be a valid E-mail")
    String email,

    @NotNull(message = "The password is required")
    @Size(min = 1, max = 16, message = "The password should has min 1 character and max 16 characters")
    String password,

    @NotNull(message = "The cpf is required")
    @CPF(message = "The cpf should be a valid CPF")
    String cpf,

    @NotNull(message = "The cep is required ")
    String cep,
    
    @NotNull(message = "The birthDate is required")
    @JsonFormat(pattern = "MM/dd/yyyy")
    LocalDate birthDate) {}
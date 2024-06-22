package com.fernando.fernando_ecommerce_api.requests;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fernando.fernando_ecommerce_api.validations.annotations.CEP;
import com.fernando.fernando_ecommerce_api.validations.annotations.Name;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ClientRequest(
    @NotNull(message = "The name is required")
    @Name(message = "The first name should be starts with uppercase letter and the last name starts with lowercase or uppercase")
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

    @NotNull(message = "The cep is required")
    @CEP(message = "The cep should be valid")
    String cep,
    
    @NotNull(message = "The birthDate is required")
    @JsonFormat(pattern = "MM/dd/yyyy")
    @PastOrPresent(message = "The birthDate should be past date or present")
    LocalDate birthDate) {}
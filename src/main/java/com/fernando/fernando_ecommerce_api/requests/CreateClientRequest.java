package com.fernando.fernando_ecommerce_api.requests;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateClientRequest(
    @NotNull(message = "The name field not should be null")
    @NotEmpty(message = "The name field not should be empty")
    String name,

    @Email(message = "The e-mail field should be in the email format")
    String email,

    @NotNull(message = "The password field not should be null")
    @Size(min = 1, max = 16, message = "The password field should has in min 1 character and max 16 characters")
    String password,

    @NotNull(message = "The cpf field not should be null")
    @CPF(message = "The cpf field should be in the cpf format")
    String cpf,
    
    @NotNull(message = "The birthDate field not should be null")
    LocalDate birthDate) {}
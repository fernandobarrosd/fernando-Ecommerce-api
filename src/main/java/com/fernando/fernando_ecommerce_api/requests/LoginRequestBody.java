package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequestBody(
    @NotNull(message = "The e-mail field not should be null")
    @Email(message = "The e-mail field should be in the email format")
    String email,

    @Size(min = 1, max = 16, message = "The password field should has in min 1 character and max 16 characters")
    String password
) {}

package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
public class AdminRequest {
    @NotNull(message = "The name field not should be null")
    @NotEmpty(message = "The name field not should be empty")
    private String name;

    @NotNull(message = "The e-mail field not should be null")
    @Email(message = "The e-mail field should be in the email format")
    private String email;

    @Setter
    @NotNull(message = "The password field not should be null")

    @Size(min = 1, max = 16, message = "The password field should has in min 1 character and max 16 characters")
    private String password;
}
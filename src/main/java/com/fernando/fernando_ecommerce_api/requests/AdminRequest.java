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

    @NotNull(message = "The e-mail is required")
    @NotEmpty(message = "The email not should be empty")
    @Email(message = "The e-mail should be a valid e-mail")
    private String email;

    @Setter
    @NotNull(message = "The password is required")

    @Size(min = 1, max = 16, message = "The password should has in min 1 character and max 16 characters")
    private String password;
}
package com.fernando.fernando_ecommerce_api.requests;

import com.fernando.fernando_ecommerce_api.validations.annotations.Name;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ProductRequest(
    @NotNull(message = "The title is required")
    @NotBlank(message = "The title should not be empty")
    @Name(message = "The first word should be starts with uppercase letter and the other words starts with lowercase or uppercase")
    String title,

    @NotNull(message = "The title is required")
    @NotBlank(message = "The title should not be empty")
    String description,

    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "The quantity should has min 1")
    Integer quantity,

    @NotNull(message = "The unitPrice is required")
    Double unitPrice
) {}
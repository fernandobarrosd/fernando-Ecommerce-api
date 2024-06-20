package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
    @NotNull(message = "The title is required")
    @NotEmpty(message = "The title should not be empty")
    String title,

    @NotNull(message = "The title is required")
    @NotEmpty(message = "The title should not be empty")
    String description,

    
    Integer quantity,
    Double unitPrice
) {}
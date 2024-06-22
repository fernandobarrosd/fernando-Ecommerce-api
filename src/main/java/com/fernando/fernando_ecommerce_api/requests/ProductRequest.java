package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ProductRequest(
    @NotNull(message = "The title is required")
    @NotBlank(message = "The title should be not empty")
    String title,

    @NotNull(message = "The description is required")
    @NotBlank(message = "The description should be not empty")
    String description,

    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "The quantity should has min 1")
    Integer quantity,

    @NotNull(message = "The unitPrice is required")
    Double unitPrice
) {}
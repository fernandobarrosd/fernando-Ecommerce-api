package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductOrderRequest(
    @NotNull(message = "The productTitle is required")
    @NotBlank(message = "The productTitle not should be empty")
    String productTile) {}
package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderRequest(
    @NotNull(message = "The products should be not null")
    @Size(min = 1, message = "The products size should be greater that zero")
    String[] products) {}
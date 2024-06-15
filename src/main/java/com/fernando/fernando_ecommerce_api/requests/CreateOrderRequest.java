package com.fernando.fernando_ecommerce_api.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOrderRequest(
    @NotNull(message = "The clientID should be not null")
    @Min(value = 1, message = "The clientID field should be greater that zero")
    Integer clientID,

    @NotNull(message = "The products should be not null")
    @Size(min = 1, message = "The products size should be greater that zero")
    String[] products) {}
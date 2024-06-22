package com.fernando.fernando_ecommerce_api.responses;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Integer id, clientID;
    @NumberFormat
    private Double totalPrice;
    private ProductResponse[] products;
}
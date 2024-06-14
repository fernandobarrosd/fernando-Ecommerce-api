package com.fernando.fernando_ecommerce_api.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreateProductRequest {
    private String title;
    private String description;
    private Integer quantity;
    @Setter
    private Double unitPrice;

}
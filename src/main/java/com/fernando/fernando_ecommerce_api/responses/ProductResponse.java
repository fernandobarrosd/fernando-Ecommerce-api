package com.fernando.fernando_ecommerce_api.responses;

import com.fernando.fernando_ecommerce_api.models.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Integer id;
    private String title;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private String createdAt;


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.unitPrice = product.getUnitPrice();
        this.createdAt = product.getCreatedAt().toString();
    }
}
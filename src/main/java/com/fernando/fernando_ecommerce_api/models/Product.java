package com.fernando.fernando_ecommerce_api.models;

import java.time.LocalDateTime;

import com.fernando.fernando_ecommerce_api.requests.CreateProductRequest;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, name = "unit_price")
    private Double unitPrice;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;


    public Product(CreateProductRequest productRequest) {
        this.title = productRequest.getTitle();
        this.description = productRequest.getDescription();
        this.quantity = productRequest.getQuantity();
        this.unitPrice = productRequest.getUnitPrice();
        this.createdAt = LocalDateTime.now();
    }
}
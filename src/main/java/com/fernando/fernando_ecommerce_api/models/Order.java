package com.fernando.fernando_ecommerce_api.models;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Product> products;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Client client;

}
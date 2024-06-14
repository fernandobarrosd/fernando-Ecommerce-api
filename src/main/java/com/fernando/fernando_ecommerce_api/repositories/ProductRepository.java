package com.fernando.fernando_ecommerce_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.fernando_ecommerce_api.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Modifying
    @Query("UPDATE Product p SET p.unitPrice = :newUnitPrice WHERE p.id = :productID")
    void updateUnitPrice(
        @Param("productID")
        Integer productID,
        
        @Param("newUnitPrice")
        Double newUnitPrice);   
}
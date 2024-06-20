package com.fernando.fernando_ecommerce_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.requests.ProductRequest;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;
import com.fernando.fernando_ecommerce_api.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest ) {
        ProductResponse productResponse = productService.saveProduct(productRequest);
        return ResponseEntity.created(null).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer productID) {
        ProductResponse productResponse = productService.findById(productID);
        return ResponseEntity.ok(productResponse);
    }

    @PatchMapping("/{productID}/unitPrice/{newUnitPrice}")
    public ResponseEntity<ProductResponse> updateUnitPrice(@PathVariable Integer productID, @PathVariable Double newUnitPrice) {
        ProductResponse productResponse = productService.updateUnitPrice(productID, newUnitPrice);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productID) {
        productService.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
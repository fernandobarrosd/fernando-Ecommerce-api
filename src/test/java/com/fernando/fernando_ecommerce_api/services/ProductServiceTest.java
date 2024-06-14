package com.fernando.fernando_ecommerce_api.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.requests.CreateProductRequest;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;

@SpringBootTest
public class ProductServiceTest {
    private CreateProductRequest productRequest;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void initProductRequest() {
        productRequest = new CreateProductRequest("Caderno", "Caderno preto do batman", 20, 25.00);
    }

    @Test
    public void shouldSaveProductWithSuccess() {
        ProductResponse productResponse = productService.saveProduct(productRequest);
        Assertions.assertNotNull(productResponse);
    }

    @Test
    public void shouldDeleteProductWithSuccess() {
        productService.saveProduct(productRequest);
        Integer productID = 1;
        Assertions.assertDoesNotThrow(() -> productService.deleteProduct(productID));
    }

    @Test
    public void shouldDeleteProductWithError() {
        Integer productID = 1;
        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(productID));
    }

    @Test
    public void shouldUpdateProductUnitPriceWithSuccess() {
        productService.saveProduct(productRequest);
        Double newUnitPrice = 50.00;
        Integer productID = 1;
        Assertions.assertDoesNotThrow(() -> productService.updateUnitPrice(productID, newUnitPrice));
    }

    @Test
    public void shouldUpdateProductUnitPriceWithError() {
        Double newUnitPrice = 50.00;
        Integer productID = 1;
        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.updateUnitPrice(productID, newUnitPrice));
    }
}
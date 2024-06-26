package com.fernando.fernando_ecommerce_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.models.Product;
import com.fernando.fernando_ecommerce_api.repositories.ProductRepository;
import com.fernando.fernando_ecommerce_api.requests.ProductRequest;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public ProductResponse saveProduct(ProductRequest productRequest) {
        if (productRepository.findByTitle(productRequest.title()).isPresent()) {
            throw new EntityAlreadyExistsException("Product#%s is already exists".formatted(productRequest.title()));
        }
        Product product = new Product(productRequest);
        productRepository.save(product);
        return new ProductResponse(product);
    }

    public void deleteProduct(Integer productID) {
        if (productRepository.findById(productID).isEmpty()) {
            throw new EntityNotFoundException("The product#%d is not exists".formatted(productID));
        }
        productRepository.deleteById(productID);
    }

    @Transactional
    public ProductResponse updateUnitPrice(Integer productID, Double newUnitPrice) {
        ProductResponse productResponse = findById(productID);
        productRepository.updateUnitPrice(productID, newUnitPrice);
        productResponse.setUnitPrice(newUnitPrice);
        return productResponse;
    }

    public List<ProductResponse> findAll() {
        return productRepository
                        .findAll()
                        .stream()
                        .map(ProductResponse::new)
                        .toList();

    }

    public ProductResponse findById(Integer productID) {
        Optional<Product> productOptional = productRepository.findById(productID);

        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("The product#%d is not exists".formatted(productID));
        }
        return new ProductResponse(productOptional.get());
    } 
}
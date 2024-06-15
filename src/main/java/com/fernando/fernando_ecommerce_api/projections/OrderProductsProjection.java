package com.fernando.fernando_ecommerce_api.projections;

import java.util.List;
import com.fernando.fernando_ecommerce_api.models.Product;
import lombok.Getter;

@Getter
public class OrderProductsProjection {
    private List<Product> products;
}
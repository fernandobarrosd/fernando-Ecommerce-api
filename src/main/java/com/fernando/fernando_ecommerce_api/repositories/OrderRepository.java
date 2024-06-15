package com.fernando.fernando_ecommerce_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fernando.fernando_ecommerce_api.models.Order;
import com.fernando.fernando_ecommerce_api.models.Product;
import com.fernando.fernando_ecommerce_api.projections.OrderProductsProjection;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query("SELECT order.products FROM Order order WHERE order.id = :orderID")
    OrderProductsProjection findAllProducts(@Param("orderID") Integer orderID);

    @Modifying
    @Query("UPDATE Order order SET order.products = :newProducts, order.totalPrice = :newTotalPrice WHERE order.id = :orderID")
    void updateProducts(
        @Param("orderID") Integer orderID, 
        @Param("newProducts") List<Product> newProducts,
        @Param("newTotalPrice") Double newTotalPrice);
}
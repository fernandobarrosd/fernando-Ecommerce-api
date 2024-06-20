package com.fernando.fernando_ecommerce_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fernando.fernando_ecommerce_api.models.Order;
import com.fernando.fernando_ecommerce_api.models.Product;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query("SELECT o.products FROM Order o WHERE o.id = :orderID")
    List<Product> findAllProducts(@Param("orderID") Integer orderID);

    @Modifying
    @Query("UPDATE Order order SET order.products = :newProducts, order.totalPrice = :newTotalPrice WHERE order.id = :orderID")
    void updateProducts(
        @Param("orderID") Integer orderID, 
        @Param("newProducts") List<Product> newProducts,
        @Param("newTotalPrice") Double newTotalPrice);
        
        
        @Query("SELECT o FROM Order o WHERE o.client.id = :clientID")
        List<Order> findAllOrdersByClientId(Integer clientID);
}
package com.fernando.fernando_ecommerce_api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.responses.OrderResponse;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;
import com.fernando.fernando_ecommerce_api.services.OrderService;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.requests.OrderRequest;
import com.fernando.fernando_ecommerce_api.requests.ProductOrderRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest, Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();
        Integer clientID = client.getId();
        OrderResponse orderResponse = orderService.saveOrder(clientID, orderRequest.products());
        return ResponseEntity.created(null).body(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();
        Integer clientID = client.getId();
        List<OrderResponse> orders = orderService.findAllOrders(clientID);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderID}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer orderID) {
        OrderResponse orderResponse = orderService.findById(orderID);
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{orderID}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderID) {
        orderService.deleteOrder(orderID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderID}/products")
    public ResponseEntity<List<ProductResponse>> findAllProductsByOrderID(@PathVariable Integer orderID) {
        List<ProductResponse> productResponses = orderService.findAllProducts(orderID);
        return ResponseEntity.ok(productResponses);
    }

    @PatchMapping("/{orderID}/products")
    public ResponseEntity<OrderResponse> addProductToOrder(@PathVariable Integer orderID, @RequestBody ProductOrderRequest productOrderRequest) {
        OrderResponse orderResponse = orderService.addProductToOrder(orderID, productOrderRequest.productTile());
        return ResponseEntity.ok(orderResponse);
    }
}
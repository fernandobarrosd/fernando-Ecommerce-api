package com.fernando.fernando_ecommerce_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.models.Order;
import com.fernando.fernando_ecommerce_api.models.Product;
import com.fernando.fernando_ecommerce_api.repositories.ClientRepository;
import com.fernando.fernando_ecommerce_api.repositories.OrderRepository;
import com.fernando.fernando_ecommerce_api.repositories.ProductRepository;
import com.fernando.fernando_ecommerce_api.responses.OrderResponse;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ProductRepository productRepository;

    private ProductResponse[] convertToProductsResponses(List<Product> products) {
        return products.stream().map(ProductResponse::new).toArray(ProductResponse[]::new);
    }

    private OrderResponse convertToOrderResponse(Order order) {
        return OrderResponse.builder()
        .id(order.getId())
        .clientID(order.getClient().getId())
        .products(convertToProductsResponses(order.getProducts()))
        .totalPrice(order.getTotalPrice())
        .build();
    }

    @Transactional
    public OrderResponse saveOrder(Integer clientID, String[] productsTitles) {
        Optional<Client> clientOptional = clientRepository.findById(clientID);
        List<String> productsNotFounded = new ArrayList<>();

        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("The client#%d is not exists".formatted(clientID));
        }
        for (String product : productsTitles) {
            if (productRepository.findByTitle(product).isEmpty()) {
                productsNotFounded.add(product);
            }
        }
        if (productsNotFounded.size() == 1) {
            String productTitle = productsNotFounded.get(0);
            throw new EntityNotFoundException("The product#%s is not exists".formatted(productTitle));
        }
        if (productsNotFounded.size() > 1) {
            throw new EntityNotFoundException("The products %s is not exists".formatted(productsNotFounded.toString()));
        }
        List<Product> products = new ArrayList<>();
        Client client = clientOptional.get();
        Double totalPrice = 0.00;

        for (String productTitle : productsTitles) {
            Product product = productRepository.findByTitle(productTitle).get();
            products.add(product);
            totalPrice += product.getUnitPrice();
        }
        
        ProductResponse[] productsResponses = convertToProductsResponses(products);
        Order order = new Order(null, totalPrice, products, client);
        Order orderSaved = orderRepository.save(order);

        return OrderResponse.builder()
        .id(orderSaved.getId())
        .products(productsResponses)
        .totalPrice(totalPrice)
        .clientID(clientID)
        .build();
    }

    public List<ProductResponse> findAllProducts(Integer orderID) {
        if (orderRepository.findById(orderID).isEmpty()) {
            throw new EntityNotFoundException("The order#%d is not exists".formatted(orderID));
        }
        return orderRepository.findAllProducts(orderID)
        .stream()
        .map(ProductResponse::new)
        .toList();
    }

    public List<OrderResponse> findAllOrders(Integer clientID) {
        if (clientRepository.findById(clientID).isEmpty()) {
            throw new EntityNotFoundException("The user#%d is not exist".formatted(clientID));
        }
        return orderRepository.findAllOrdersByClientId(clientID)
        .stream()
        .map(this::convertToOrderResponse)
        .toList();
    }

    @Transactional
    public OrderResponse addProductToOrder(Integer orderID, String productTitle) {
        Optional<Product> productOptional = productRepository.findByTitle(productTitle);
        Optional<Order> orderOptional = orderRepository.findById(orderID);

        if (orderOptional.isEmpty()) {
            throw new EntityNotFoundException("The order#%d is not exists".formatted(orderID));
        }
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("The product#%s is not exists".formatted(productTitle));
        }
        Product product = productOptional.get();
        Order order = orderOptional.get();


        List<Product> products = order.getProducts();
        Double totalPrice = order.getTotalPrice();

        
        products.add(product);
        Double newTotalPrice = totalPrice += product.getUnitPrice();

        orderRepository.updateProducts(orderID, products, newTotalPrice);

        ProductResponse[] productsResponses = convertToProductsResponses(products);

        return OrderResponse.builder()
        .id(orderID)
        .totalPrice(totalPrice)
        .products(productsResponses)
        .clientID(order.getClient().getId())
        .build();
    }

    public OrderResponse findById(Integer orderID) {
        Optional<Order> orderOptional = orderRepository.findById(orderID);
        if (orderOptional.isEmpty()) {
            throw new EntityNotFoundException("The order#%d is not exists".formatted(orderID));
        }
        Order order = orderOptional.get();
        ProductResponse[] productsResponses = convertToProductsResponses(order.getProducts());
        return OrderResponse.builder()
        .id(orderID)
        .totalPrice(order.getTotalPrice())
        .products(productsResponses)
        .clientID(order.getClient().getId())
        .build();
    }

    public void deleteOrder(Integer orderID) {
        if (orderRepository.findById(orderID).isEmpty()) {
            throw new EntityNotFoundException("The order#%d is not exists".formatted(orderID));
        }
        orderRepository.deleteById(orderID);
    }
}
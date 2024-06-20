package com.fernando.fernando_ecommerce_api.services;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;
import com.fernando.fernando_ecommerce_api.requests.ProductRequest;
import com.fernando.fernando_ecommerce_api.responses.OrderResponse;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;


@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    private ProductRequest[] productRequests;

    private ClientRequest clientRequest;

    @BeforeEach
    public void setup() {
        clientRequest = new ClientRequest("Joaquim",
         "joaquim@test.com", 
         "joaquim123",
          "000.000.000-00",
          "11111-111",
        LocalDate.of(1980, 12, 10));

        productRequests = new ProductRequest[]{
            new ProductRequest(
            "Caderno",
            "Caderno do batman",
            20,
            20.00),

            new ProductRequest(
            "Estojo",
            "Estojo do batman",
            20,
            50.00),

            new ProductRequest(
            "Mochila do homem aranha",
            "Mochila do homem aranha com o ziper de cor vermelha",
            15,
            45.00)
        };

    }

    
    @Test
    public void shouldSaveOrderWithSuccess() {
        var product1 = productService.saveProduct(productRequests[0]);
        var product2 = productService.saveProduct(productRequests[1]);
        var product3 = productService.saveProduct(productRequests[2]);
        clientService.saveClient(clientRequest);

        var productsTitles = new String[]{ product1.getTitle(), product2.getTitle(), product3.getTitle() };

        
        var totalPrice = 115.00;
        OrderResponse orderResponse = Assertions.assertDoesNotThrow(() -> orderService.saveOrder(1, productsTitles));
        Assertions.assertNotNull(orderResponse);
        Assertions.assertEquals(totalPrice, orderResponse.getTotalPrice());
        var productsTitles2 = List.of(orderResponse.getProducts()).stream()
        .map(o -> o.getTitle())
        .toArray(String[]::new);
        Assertions.assertArrayEquals(productsTitles, productsTitles2);
    }

    @Test
    public void shouldNotSaveOrderIfClientNotExists() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.saveOrder(1, new String[]{}));
    }

    @Test
    public void shouldNotSaveOrderIfProductsNotExistis() {
        clientService.saveClient(clientRequest);
        String[] productsTitles = new String[]{
            productRequests[0].title(),
            productRequests[1].title(),
            productRequests[2].title()
        };
        Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.saveOrder(1, productsTitles));
    }
}
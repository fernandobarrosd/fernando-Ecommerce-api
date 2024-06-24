package com.fernando.fernando_ecommerce_api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.responses.OrderResponse;
import com.fernando.fernando_ecommerce_api.responses.ProductResponse;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseErrorWithFields;
import com.fernando.fernando_ecommerce_api.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.requests.OrderRequest;
import com.fernando.fernando_ecommerce_api.requests.ProductOrderRequest;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @Operation(summary = "Save order")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            content = {
                @Content(
                    schema = @Schema(implementation = OrderResponse.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseErrorWithFields.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest, Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();
        Integer clientID = client.getId();
        OrderResponse orderResponse = orderService.saveOrder(clientID, orderRequest.products());
        return ResponseEntity.created(null).body(orderResponse);
    }

    @Operation(summary = "List all client orders")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    array = @ArraySchema(
                        schema = @Schema(implementation = OrderResponse.class)
                    )
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
    })
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();
        Integer clientID = client.getId();
        List<OrderResponse> orders = orderService.findAllOrders(clientID);
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Find order by id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    schema = @Schema(implementation = OrderResponse.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @GetMapping("/{orderID}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer orderID) {
        OrderResponse orderResponse = orderService.findById(orderID);
        return ResponseEntity.ok(orderResponse);
    }

    @Operation(summary = "Delete order by id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200"
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @DeleteMapping("/{orderID}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderID) {
        orderService.deleteOrder(orderID);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List order products")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    array = @ArraySchema(
                        schema = @Schema(implementation = OrderResponse.class)
                    )
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @GetMapping("/{orderID}/products")
    public ResponseEntity<List<ProductResponse>> findAllProductsByOrderID(@PathVariable Integer orderID) {
        List<ProductResponse> productResponses = orderService.findAllProducts(orderID);
        return ResponseEntity.ok(productResponses);
    }

    @Operation(summary = "Add product to order")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    schema = @Schema(implementation = OrderResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "401",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseError.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            content = {
                @Content(
                    schema = @Schema(implementation = ResponseErrorWithFields.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @PatchMapping("/{orderID}/products")
    public ResponseEntity<OrderResponse> addProductToOrder(@PathVariable Integer orderID, @RequestBody ProductOrderRequest productOrderRequest) {
        OrderResponse orderResponse = orderService.addProductToOrder(orderID, productOrderRequest.productTile());
        return ResponseEntity.ok(orderResponse);
    }
}
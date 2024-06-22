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
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseErrorWithFields;
import com.fernando.fernando_ecommerce_api.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Save product")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            content = {
                @Content(
                    schema = @Schema(implementation = ProductResponse.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
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
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest ) {
        ProductResponse productResponse = productService.saveProduct(productRequest);
        return ResponseEntity.created(null).body(productResponse);
    }


    @Operation(summary = "List all products")
    @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    array = @ArraySchema(
                        schema = @Schema(implementation = ProductResponse.class)
                    ),
                    mediaType = "application/json"
                )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(summary = "Find product by id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    schema = @Schema(implementation = ProductResponse.class),
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
        )
    })
    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer productID) {
        ProductResponse productResponse = productService.findById(productID);
        return ResponseEntity.ok(productResponse);
    }

    @Operation(summary = "Update product unit price")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    schema = @Schema(implementation = ProductResponse.class),
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
    @PatchMapping("/{productID}/unitPrice/{newUnitPrice}")
    public ResponseEntity<ProductResponse> updateUnitPrice(@PathVariable Integer productID, @PathVariable Double newUnitPrice) {
        ProductResponse productResponse = productService.updateUnitPrice(productID, newUnitPrice);
        return ResponseEntity.ok(productResponse);
    }

    @Operation(summary = "Delete product")
    @ApiResponses({
        @ApiResponse(
            responseCode = "204"
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
    @DeleteMapping("/{productID}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productID) {
        productService.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }
}
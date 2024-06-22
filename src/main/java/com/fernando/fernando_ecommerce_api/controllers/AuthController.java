package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.fernando_ecommerce_api.requests.LoginRequest;
import com.fernando.fernando_ecommerce_api.responses.LoginResponse;
import com.fernando.fernando_ecommerce_api.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Login admin or client")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                    schema = @Schema(implementation = LoginResponse.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            content = {
                @Content(
                    schema = @Schema(implementation = LoginResponse.class),
                    mediaType = "application/json"
                )
            }
        )
    })
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
}
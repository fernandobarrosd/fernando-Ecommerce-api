package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.fernando_ecommerce_api.requests.LoginRequest;
import com.fernando.fernando_ecommerce_api.responses.LoginResponse;
import com.fernando.fernando_ecommerce_api.services.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
}
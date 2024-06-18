package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.requests.AdminRequest;
import com.fernando.fernando_ecommerce_api.responses.AdminResponse;
import com.fernando.fernando_ecommerce_api.services.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponse> registerAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        AdminResponse adminResponse = adminService.saveAdmin(adminRequest);
        return ResponseEntity.created(null).body(adminResponse);
    }
}
package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.requests.AdminRequest;
import com.fernando.fernando_ecommerce_api.responses.AdminResponse;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseErrorWithFields;
import com.fernando.fernando_ecommerce_api.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Operation(summary = "Register admin")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            content = {
                @Content(
                    schema = @Schema(implementation = AdminResponse.class),
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
    public ResponseEntity<AdminResponse> registerAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        AdminResponse adminResponse = adminService.saveAdmin(adminRequest);
        return ResponseEntity.created(null).body(adminResponse);
    }
}
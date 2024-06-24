package com.fernando.fernando_ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.fernando_ecommerce_api.requests.ClientRequest;
import com.fernando.fernando_ecommerce_api.responses.ClientResponse;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseErrorWithFields;
import com.fernando.fernando_ecommerce_api.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
@Tag(name = "Client")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @Operation(summary = "Register client")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            content = {
                @Content(
                    schema = @Schema(implementation = ClientResponse.class),
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
    public ResponseEntity<ClientResponse> registerClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.saveClient(clientRequest);
        return ResponseEntity.created(null).body(clientResponse);
    }
}

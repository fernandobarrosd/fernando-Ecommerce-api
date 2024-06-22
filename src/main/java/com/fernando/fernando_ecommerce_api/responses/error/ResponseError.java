package com.fernando.fernando_ecommerce_api.responses.error;

import lombok.Builder;

@Builder
public record ResponseError(
    String message,
    Integer statusCode,
    String path) {
        
        public String toJson() {
            return "{" + "\n" +
                    "\t" + "\"message\": \"%s\"".formatted(message) + "," + "\n" +
                    "\t" + "\"statusCode\": %d".formatted(statusCode) + "," + "\n" +
                    "\t" + "\"path\": \"%s\"".formatted(path) + "\n" +
                   "}";
        }
    }
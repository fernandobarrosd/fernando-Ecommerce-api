package com.fernando.fernando_ecommerce_api.responses.error;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record ResponseErrorWithFields(
    Integer statusCode,
    String path,
    List<Map<String, String>> fields) {}
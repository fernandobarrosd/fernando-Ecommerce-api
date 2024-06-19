package com.fernando.fernando_ecommerce_api.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseErrorWithFields;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ResponseError> handleEntityAlreadyExists(
        EntityAlreadyExistsException exception,
        HttpServletRequest request) {
            ResponseError responseError = ResponseError.builder()
            .message(exception.getMessage())
            .path(request.getRequestURI())
            .statusCode(HttpStatus.CONFLICT.value())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> handleEntityNotFound(
        EntityNotFoundException exception,
        HttpServletRequest request) {
            ResponseError responseError = ResponseError.builder()
            .message(exception.getMessage())
            .path(request.getRequestURI())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorWithFields> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpServletRequest request
    ) {
        List<Map<String, String>> fields = exception.getFieldErrors()
        .stream()
        .map(this::convertToMap)
        .toList();

        ResponseErrorWithFields responseError = ResponseErrorWithFields.builder()
            .path(request.getRequestURI())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .fields(fields)
            .build();
        return ResponseEntity.badRequest().body(responseError);
    }

    private Map<String, String> convertToMap(FieldError fieldError) {
        Map<String, String> field = new HashMap<>();
        field.put("name", fieldError.getField());
        field.put("errorMessage", fieldError.getDefaultMessage());
        return field;
    }
}
package com.fernando.fernando_ecommerce_api.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.responses.error.ResponseError;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {
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
}
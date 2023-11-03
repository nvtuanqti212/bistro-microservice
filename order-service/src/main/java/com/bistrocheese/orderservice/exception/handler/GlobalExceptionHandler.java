package com.bistrocheese.orderservice.exception.handler;

import com.bistrocheese.orderservice.dto.response.ErrorResponse;
import com.bistrocheese.orderservice.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getApiStatus().getMessage(),
                exception.getApiStatus().name(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(exception.getApiStatus().getStatus()).body(errorResponse);
    }
}



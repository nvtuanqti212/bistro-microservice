package com.bistrocheese.foodservice.exception.handler;

import com.bistrocheese.foodservice.dto.response.ErrorResponse;
import com.bistrocheese.foodservice.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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



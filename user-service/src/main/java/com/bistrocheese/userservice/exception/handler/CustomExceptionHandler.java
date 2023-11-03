package com.bistrocheese.userservice.exception.handler;

import com.bistrocheese.userservice.dto.response.ErrorResponse;
import com.bistrocheese.userservice.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(e.getHttpStatus().toString());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(String.valueOf(now));
        return ResponseEntity.badRequest().body(errorResponse);
    }
}

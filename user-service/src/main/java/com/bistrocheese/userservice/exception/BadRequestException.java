package com.bistrocheese.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class BadRequestException extends CustomException{
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

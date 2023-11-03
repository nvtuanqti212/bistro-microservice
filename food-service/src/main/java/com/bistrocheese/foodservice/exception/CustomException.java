package com.bistrocheese.foodservice.exception;

import com.bistrocheese.foodservice.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final APIStatus apiStatus;
}
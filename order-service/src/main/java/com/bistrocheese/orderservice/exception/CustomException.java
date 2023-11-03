package com.bistrocheese.orderservice.exception;


import com.bistrocheese.orderservice.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final APIStatus apiStatus;
}
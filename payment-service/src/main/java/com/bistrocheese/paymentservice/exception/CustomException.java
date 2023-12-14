package com.bistrocheese.paymentservice.exception;

import com.bistrocheese.paymentservice.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final APIStatus apiStatus;
}
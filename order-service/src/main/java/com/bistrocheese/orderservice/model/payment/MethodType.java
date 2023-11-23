package com.bistrocheese.orderservice.model.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MethodType {
    BANK_TRANSFER,
    DIGITAL_WALLET;
}

package com.bistrocheese.orderservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING,
    SHIPPING,
    COMPLETED,
    CANCEL
}

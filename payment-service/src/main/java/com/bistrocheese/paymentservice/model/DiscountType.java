package com.bistrocheese.paymentservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountType {
    PERCENTAGE,
    FIXED
}

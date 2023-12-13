package com.bistrocheese.userservice.dto.request.bill;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    CASH,
    TRANSFER;
}

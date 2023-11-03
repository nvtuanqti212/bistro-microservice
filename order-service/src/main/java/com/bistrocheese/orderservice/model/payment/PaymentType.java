package com.bistrocheese.orderservice.model.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    MOMO,
    GOOGLE_PAY,
    APPLE_PAY,
    OTHERS;
    public static PaymentType covertIntToPaymentType(int paymentType) {
        return switch (paymentType) {
            case 0 -> CASH;
            case 1 -> CREDIT_CARD;
            case 2 -> DEBIT_CARD;
            case 3 -> PAYPAL;
            case 4 -> MOMO;
            case 5 -> GOOGLE_PAY;
            case 6 -> APPLE_PAY;
            default -> OTHERS;
        };
    }
}

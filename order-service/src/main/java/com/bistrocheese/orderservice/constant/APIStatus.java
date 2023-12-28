package com.bistrocheese.orderservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIStatus {

    // Common status
    ERR_INTERNAL_SERVER(500, "Internal Error"),
    ERR_BAD_REQUEST(400, "Bad request"),

    //    Order
    ORDER_NOT_FOUND(404, "Order not found"),
    ORDER_TABLE_NOT_FOUND(404, "Order table not found"),
    INGREDIENT_NOT_ENOUGH(400, "Ingredient not enough"),
    ORDER_LINE_NOT_FOUND(404, "Order line not found"),
    ORDER_TABLE_NOT_EMPTY(400, "Order table not empty"),
    ORDER_CREATE_FAILED(400, "Order create failed"),

    //    Payment
    ORDER_COMPLETED(400, "Order already completed"),

    // Food
    FOOD_NOT_FOUND(404, "Food not found"),

    // User
    USER_NOT_FOUND(404, "User not found");

    private final int status;
    private final String message;
}

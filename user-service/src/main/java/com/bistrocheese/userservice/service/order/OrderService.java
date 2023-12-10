package com.bistrocheese.userservice.service.order;

import com.bistrocheese.userservice.dto.request.order.OrderCreateRequest;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    public CompletableFuture<String> createOrder(OrderCreateRequest request);
}

package com.bistrocheese.userservice.service.order;

import com.bistrocheese.userservice.dto.request.order.OrderCreateRequest;
import com.bistrocheese.userservice.dto.response.OrderResponse;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    public CompletableFuture<String> createOrder(OrderCreateRequest request);
    public OrderResponse getOrderById(String orderId);
}

package com.bistrocheese.orderservice.service;


import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.model.Order;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<Order>  createOrder(OrderCreateRequest request);
    void delete(UUID orderId);
    Order getById(UUID orderId);
    List<Order> getOrdersByUserId(String userId);
}

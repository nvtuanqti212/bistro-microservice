package com.bistrocheese.orderservice.service;


import com.bistrocheese.orderservice.model.Order;

import java.util.UUID;

public interface OrderService {
    void createOrder(String staffId);
    void delete(UUID orderId);
    Order getById(UUID orderId);

}

package com.bistrocheese.orderservice.service;


import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.dto.response.OrderCreateResponse;
import com.bistrocheese.orderservice.dto.response.OrderDeleteResponse;

import java.util.UUID;

public interface OrderService {
    OrderCreateResponse createOrder(OrderCreateRequest orderResponseRequest);
    OrderDeleteResponse deleteOrder(UUID id);
}

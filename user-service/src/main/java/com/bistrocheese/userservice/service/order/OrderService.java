package com.bistrocheese.userservice.service.order;

import reactor.core.publisher.Mono;

public interface OrderService {
    public String createOrder(String staffId);
}

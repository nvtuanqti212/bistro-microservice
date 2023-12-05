package com.bistrocheese.userservice.service.order;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    public CompletableFuture<String> createOrder(String staffId) throws InterruptedException;
}

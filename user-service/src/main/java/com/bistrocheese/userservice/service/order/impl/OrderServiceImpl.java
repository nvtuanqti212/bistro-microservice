package com.bistrocheese.userservice.service.order.impl;

import com.bistrocheese.userservice.client.OrderFeignClient;
import com.bistrocheese.userservice.repository.user.StaffRepository;
import com.bistrocheese.userservice.service.order.OrderService;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.bistrocheese.userservice.constant.ServiceConstant.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StaffRepository staffRepository;
    private final OrderFeignClient orderFeignClient;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Bulkhead(name = ORDER_SERVICE_BULKHEAD, type = Bulkhead.Type.THREADPOOL, fallbackMethod = "fallback")
    @CircuitBreaker(name = ORDER_SERVICE_CB, fallbackMethod = "fallback")
    public CompletableFuture<String> createOrder(String staffId) throws InterruptedException {
        logger.info("Creating order...");
        Thread.sleep(3000);
        staffRepository.findById(staffId).orElseThrow(
                () -> new RuntimeException("Staff not found")
        );
        ResponseEntity<String> res = orderFeignClient.placeOrder(staffId);
        return CompletableFuture.completedFuture(res.getBody());
    }

    private CompletableFuture<String> fallback(String staffId, CallNotPermittedException e) {
        return CompletableFuture.completedFuture("Recovered specific CallNotPermittedException: " + e.toString());
    }

    private CompletableFuture<String> fallback(String staffId, BulkheadFullException e) {
        return CompletableFuture.completedFuture("Recovered specific BulkheadFullException: " + e.toString());
    }
}

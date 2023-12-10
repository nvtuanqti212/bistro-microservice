package com.bistrocheese.userservice.service.order.impl;

import com.bistrocheese.userservice.client.FoodFeignClient;
import com.bistrocheese.userservice.client.OrderFeignClient;
import com.bistrocheese.userservice.dto.request.order.OrderCreateRequest;
import com.bistrocheese.userservice.dto.request.order.OrderLineRequest;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.bistrocheese.userservice.constant.ServiceConstant.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StaffRepository staffRepository;
    private final OrderFeignClient orderFeignClient;
    private final FoodFeignClient foodFeignClient;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Bulkhead(name = ORDER_SERVICE_BULKHEAD, type = Bulkhead.Type.THREADPOOL, fallbackMethod = "fallback")
    @CircuitBreaker(name = ORDER_SERVICE_CB, fallbackMethod = "fallback")
    public CompletableFuture<String> createOrder(OrderCreateRequest request){
        String staffId = request.getStaffId();
        List<OrderLineRequest> orderLines = request.getOrderLines();

        logger.info("Creating order...");
        staffRepository.findById(staffId).orElseThrow(
                () -> new RuntimeException("Staff not found")
        );

        Flux.fromIterable(orderLines)
                .flatMap(orderLine -> {
                    UUID foodId = orderLine.getFoodId();
                    if (!checkIfFoodExist(foodId)) {
                        return Flux.error(new RuntimeException("Food not found"));
                    }
                    return Mono.just(orderLine);
                })
                .collectList()
                .block();

        ResponseEntity<String> res = orderFeignClient.placeOrder(request);
        return CompletableFuture.completedFuture(res.getBody());
    }

    private CompletableFuture<String> fallback(OrderCreateRequest request, CallNotPermittedException e) {
        return CompletableFuture.completedFuture("Recovered specific CallNotPermittedException: " + e.toString());
    }

    private CompletableFuture<String> fallback(OrderCreateRequest request, BulkheadFullException e) {
        return CompletableFuture.completedFuture("Recovered specific BulkheadFullException: " + e.toString());
    }

    private boolean checkIfFoodExist(UUID foodId) {
        return foodFeignClient.getDetailFood(foodId) != null;
    }
}

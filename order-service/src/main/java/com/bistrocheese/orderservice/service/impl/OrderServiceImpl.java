package com.bistrocheese.orderservice.service.impl;


import com.bistrocheese.orderservice.client.FoodFeignClient;
import com.bistrocheese.orderservice.client.UserFeignClient;
import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.dto.request.OrderLineRequest;
import com.bistrocheese.orderservice.dto.response.FoodResponse;
import com.bistrocheese.orderservice.dto.response.order.UserResponse;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.*;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.OrderLineService;
import com.bistrocheese.orderservice.service.OrderService;
import com.bistrocheese.orderservice.service.RedisHashService;
import com.bistrocheese.orderservice.utils.MapperUtils;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.bistrocheese.orderservice.constant.RedisConstant.ORDERS;
import static com.bistrocheese.orderservice.constant.ServiceConstant.ORDER_SERVICE_BULKHEAD;
import static com.bistrocheese.orderservice.constant.ServiceConstant.ORDER_SERVICE_CB;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final OrderLineService orderLineService;
    private final FoodFeignClient foodFeignClient;
    private final UserFeignClient userFeignClient;
    private final RedisHashService redisHashService;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @CircuitBreaker(name = ORDER_SERVICE_CB, fallbackMethod = "fallback")
    @Bulkhead(name = ORDER_SERVICE_BULKHEAD, type = Bulkhead.Type.THREADPOOL, fallbackMethod = "fallback")
    @Transactional(rollbackFor = CustomException.class)
    public CompletableFuture<Order> createOrder(OrderCreateRequest req) {
        String staffId = req.getStaffId();

        List<OrderLineRequest> orderLineList = req.getOrderLines();

        Optional<UserResponse> userResponse = userFeignClient.getUser(staffId);

        //TODO: Custom Exception not working as expected
        if (userResponse.isEmpty()) {
            throw new RuntimeException(APIStatus.USER_NOT_FOUND.toString());
        }

        Order newOrder = Order.builder()
                .staffId(userResponse.get().getId())
                .status(OrderStatus.PENDING)
                .totalPrice(BigDecimal.valueOf(0))
                .createdAt(Timestamp.from(new Date().toInstant()))
                .build();

        Order createdOrder = orderRepository.save(newOrder);

        redisHashService.hSet(ORDERS, createdOrder.getId().toString(), createdOrder);

        //Todo: How to use reactive programming here? because asynchronous cause to change total_price of order concurrently -> blocking
        orderLineList.forEach(orderLineRequest -> {
            ResponseEntity<FoodResponse> foodResponse = foodFeignClient.getDetailFood(orderLineRequest.getFoodId());
            if (foodResponse.getStatusCode().isError()) {
                orderRepository.delete(createdOrder);
                throw new CustomException(APIStatus.FOOD_NOT_FOUND);
            }
            orderLineRequest.setPrice(foodResponse.getBody().getPrice());
            orderLineService.create(createdOrder.getId(), orderLineRequest);
        });


        return CompletableFuture.completedFuture(orderRepository.findById(createdOrder.getId()).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_CREATE_FAILED)
        ));
    }

    @Override
    public void delete(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        // delete order_lines of order
        List<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(orderId);
        orderLineList.stream().map(OrderLine::getId).forEach(orderLineService::delete);

        // delete order
        orderRepository.delete(order);
    }

    @Override
    public Order getById(UUID orderId) {
        Object order = redisHashService.hGet(ORDERS, orderId.toString());
        if (order != null) {
            return MapperUtils.objectMapper(order, Order.class);
        }
        return orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findAllByStaffId(userId);
    }

    private CompletableFuture<Order> fallback(OrderCreateRequest request, BulkheadFullException e) {
        Order defaultOrder = Order.builder()
                .staffId("BulkheadFullException")
                .status(OrderStatus.PENDING)
                .totalPrice(BigDecimal.valueOf(0))
                .createdAt(Timestamp.from(new Date().toInstant()))
                .build();
        logger.error("BulkheadFullException: {}", e.toString());
        return CompletableFuture.completedFuture(defaultOrder);
    }

    private CompletableFuture<Order> fallback(OrderCreateRequest request, CallNotPermittedException e) {
        Order defaultOrder = Order.builder()
                .staffId("CircleBreaker Open")
                .status(OrderStatus.PENDING)
                .totalPrice(BigDecimal.valueOf(0))
                .createdAt(Timestamp.from(new Date().toInstant()))
                .build();
        logger.error("BulkheadFullException: {}", e.toString());
        return CompletableFuture.completedFuture(defaultOrder);
    }

}

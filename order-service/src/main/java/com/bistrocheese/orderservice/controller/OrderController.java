package com.bistrocheese.orderservice.controller;


import com.bistrocheese.orderservice.constant.RouteConstant;
import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.model.Order;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.ORDERS)
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> placeOrder(@RequestBody OrderCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request).join());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrdersByUser(@RequestBody String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping(RouteConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") UUID orderId) {
        return ResponseEntity.ok(orderService.getById(orderId));
    }
}

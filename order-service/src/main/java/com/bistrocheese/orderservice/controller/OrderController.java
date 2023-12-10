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

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.ORDERS)
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping()
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@RequestBody OrderCreateRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.ok("Order Created");
    }

    //TODO: Get Orders
    //TODO: Delete Order
}

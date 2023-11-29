package com.bistrocheese.orderservice.controller;


import com.bistrocheese.orderservice.constant.RouteConstant;
import com.bistrocheese.orderservice.model.Order;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //TODO: Get Orders
    //TODO: Delete Order
}

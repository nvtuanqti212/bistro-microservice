package com.bistrocheese.orderservice.controller;


import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@PathVariable("userId") String userId) {
        orderService.createOrder(userId);
        return ResponseEntity.ok("Order Created");
    }

    //TODO: Get Orders
    //TODO: Delete Order
}

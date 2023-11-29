package com.bistrocheese.orderservice.controller;

import com.bistrocheese.orderservice.constant.RouteConstant;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.ORDERS)
public class UserController {
    private final OrderService orderService;

    @PostMapping("users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@PathVariable("userId") String userId) {
        orderService.createOrder(userId);
        return ResponseEntity.ok("Order Created");
    }
}

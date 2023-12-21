package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.order.OrderCreateRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.dto.response.OrderResponse;
import com.bistrocheese.userservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.USERS)
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @PostMapping(RouteConstant.ORDER)
    public ResponseEntity<MessageResponse> placeOrder(@RequestBody OrderCreateRequest req) throws InterruptedException {
        String message = orderService.createOrder(req).join();
        logger.info("Order created: {}", message);
        return ResponseEntity.ok(
                new MessageResponse(message)
        );
    }

    @GetMapping(RouteConstant.ORDER_ID)
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") String orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(orderId));
    }

}

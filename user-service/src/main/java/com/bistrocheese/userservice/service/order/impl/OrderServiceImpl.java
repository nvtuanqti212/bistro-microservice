package com.bistrocheese.userservice.service.order.impl;

import com.bistrocheese.userservice.client.OrderFeignClient;
import com.bistrocheese.userservice.repository.user.StaffRepository;
import com.bistrocheese.userservice.service.order.OrderService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.bistrocheese.userservice.constant.ServiceConstant.ORDER_SERVICE;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StaffRepository staffRepository;
    private final OrderFeignClient orderFeignClient;


    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "fallback")
    public String createOrder(String staffId) {
        staffRepository.findById(staffId).orElseThrow(
                () -> new RuntimeException("Staff not found")
        );

        ResponseEntity<String> res = orderFeignClient.placeOrder(staffId);
        if (res.getStatusCode().is2xxSuccessful()) {
            logger.info("Staff with id {} created order successfully", staffId);
            return "Order created";
        } else {
            logger.warn("Staff with id {} created order fail", staffId);
            return "Order creation fail";
        }
    }

    private String fallback(String staffId, CallNotPermittedException e) {
        logger.info("Circuit breaker is open");
        return e.getMessage();
    }
}

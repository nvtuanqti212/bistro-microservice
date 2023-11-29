package com.bistrocheese.userservice.service.order.impl;


import com.bistrocheese.userservice.client.OrderFeignClient;
import com.bistrocheese.userservice.exception.CustomException;
import com.bistrocheese.userservice.repository.user.StaffRepository;
import com.bistrocheese.userservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StaffRepository staffRepository;
    private final OrderFeignClient orderFeignClient;


    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void createOrder(String staffId) {

        staffRepository.findById(staffId).orElseThrow(
                () -> new RuntimeException("Staff not found")
        );

        logger.info("staffId: {}", staffId);
        ResponseEntity<String> res = orderFeignClient.placeOrder(staffId);
        if (res.getStatusCode().is2xxSuccessful()) {
            logger.info("Order created");
        } else {
            logger.info("Order creation failed");
        }
    }
}

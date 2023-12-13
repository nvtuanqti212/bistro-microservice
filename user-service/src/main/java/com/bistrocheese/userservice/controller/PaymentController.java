package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.bill.BillRequest;
import com.bistrocheese.userservice.service.bill.BillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.USERS)
public class PaymentController {

    private final BillServiceImpl billService;
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @PostMapping(RouteConstant.PAYMENT)
    public ResponseEntity<String> createBill(@RequestBody BillRequest req) {
        billService.create(req);
        logger.info("Bill created: {}", req);

        return ResponseEntity.status(201).body("Create bill successfully");
    }
}

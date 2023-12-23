package com.bistrocheese.paymentservice.controller;

import com.bistrocheese.paymentservice.constant.APIConstant;
import com.bistrocheese.paymentservice.dto.request.BillRequest;
import com.bistrocheese.paymentservice.dto.response.MessageResponse;
import com.bistrocheese.paymentservice.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.BILL)
public class BillController {

    private final BillService billService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> create(@RequestBody BillRequest billRequest) {
        billService.create(billRequest);
        return ResponseEntity.status(201).body(new MessageResponse("Create bill successfully"));
    }
}

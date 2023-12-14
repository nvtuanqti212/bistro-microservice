package com.bistrocheese.userservice.client;

import com.bistrocheese.userservice.dto.request.bill.BillRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.bistrocheese.userservice.constant.ServiceConstant.PAYMENT_SERVICE;

@FeignClient(PAYMENT_SERVICE)
public interface PaymentFeignClient {
    @RequestMapping(value = "/payment-service/api/bills", method = RequestMethod.POST)
    ResponseEntity<MessageResponse> create(@RequestBody BillRequest billRequest);
}

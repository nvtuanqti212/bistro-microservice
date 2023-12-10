package com.bistrocheese.userservice.client;

import com.bistrocheese.userservice.dto.request.order.OrderCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.bistrocheese.userservice.constant.ServiceConstant.ORDER_SERVICE;

@FeignClient(ORDER_SERVICE)
public interface OrderFeignClient {
    @RequestMapping(value = "order-service/api/orders", method = RequestMethod.GET)
    ResponseEntity<String> placeOrder(@RequestBody OrderCreateRequest request);
}




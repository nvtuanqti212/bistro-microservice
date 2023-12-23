package com.bistrocheese.paymentservice.client;

import com.bistrocheese.paymentservice.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

import static com.bistrocheese.paymentservice.constant.ServiceConstant.ORDER_SERVICE;


@FeignClient(ORDER_SERVICE)
public interface OrderFeignClient {
    @RequestMapping(value = "order-service/api/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") UUID orderId);
}




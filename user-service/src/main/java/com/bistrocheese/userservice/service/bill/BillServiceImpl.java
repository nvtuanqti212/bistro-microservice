package com.bistrocheese.userservice.service.bill;

import com.bistrocheese.userservice.client.OrderFeignClient;
import com.bistrocheese.userservice.client.PaymentFeignClient;
import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.bill.BillRequest;
import com.bistrocheese.userservice.dto.response.OrderResponse;
import com.bistrocheese.userservice.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{

    private final OrderFeignClient orderFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    @Override
    public void create(BillRequest billRequest) {
        OrderResponse orderResponse = orderFeignClient.getOrderById(billRequest.getOrderId()).getBody();
        if (orderResponse == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, MessageConstant.ORDER_NOT_FOUND);
        }

        billRequest.setSubTotal(orderResponse.getTotalPrice());
        paymentFeignClient.create(billRequest);
    }
}

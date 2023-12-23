package com.bistrocheese.paymentservice.service.impl;

import com.bistrocheese.paymentservice.client.OrderFeignClient;
import com.bistrocheese.paymentservice.constant.APIStatus;
import com.bistrocheese.paymentservice.dto.request.BillRequest;
import com.bistrocheese.paymentservice.dto.response.OrderResponse;
import com.bistrocheese.paymentservice.exception.CustomException;
import com.bistrocheese.paymentservice.model.Bill;
import com.bistrocheese.paymentservice.model.Discount;
import com.bistrocheese.paymentservice.publisher.BillPublisher;
import com.bistrocheese.paymentservice.repository.BillRepository;
import com.bistrocheese.paymentservice.service.BillService;
import com.bistrocheese.paymentservice.service.DiscountService;
import com.bistrocheese.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;



@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    private final DiscountService discountService;
    private final OrderFeignClient orderFeignClient;
    private final BillPublisher billPublisher;
    private final static Logger logger = LogManager.getLogger(BillServiceImpl.class);

    @Override
    public void create(BillRequest billRequest) {

        if (billRepository.findByOrderId(billRequest.getOrderId()).isPresent()) {
            throw new CustomException(APIStatus.ORDER_PAID);
        }

        OrderResponse order = orderFeignClient.getOrderById(billRequest.getOrderId()).getBody();

        if (order == null) {
            throw new CustomException(APIStatus.ORDER_NOT_FOUND);
        }

        Bill bill = new Bill();
        BigDecimal subTotalOrder = order.getTotalPrice();

        // Create payment
//        PaymentRequest paymentRequest = copyProperties(billRequest, PaymentRequest.class);
//        Payment payment = paymentService.create(paymentRequest);

        // Find discount by id
        if (billRequest.getDiscountId() != null) {
            Discount discount = discountService.getById(billRequest.getDiscountId());
            bill.setDiscount(discount);
        }

        BigDecimal total = subTotalOrder.subtract(discountService.calculateDiscount(subTotalOrder, bill.getDiscount()));

        bill.setOrderId(billRequest.getOrderId());
//        bill.setPayment(payment);
        bill.setTotal(total);
        bill.setSubTotal(billRequest.getSubTotal());
        bill.setPaid(billRequest.getPaid());
        bill.setChange(billRequest.getPaid().subtract(total));

        bill.setCusIn(order.getCreatedAt());
        bill.setCusOut(new Date());
        try {
            billPublisher.completeOrder(billRequest.getOrderId());
        } catch (Exception e) {
            logger.error("Error payment service while sending message to queue: {}", e.getMessage());
        }
        billRepository.save(bill);
    }
}

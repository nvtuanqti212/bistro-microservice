package com.bistrocheese.orderservice.service.impl;


import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.dto.request.OrderLineRequest;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.*;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.OrderLineService;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final OrderLineService orderLineService;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public void createOrder(OrderCreateRequest req) {
        String staffId = req.getStaffId();

        Flux<OrderLineRequest> orderLineList = Flux.fromIterable(req.getOrderLines());
        Order newOrder = Order.builder()
                .staffId(staffId)
                .status(OrderStatus.PENDING)
                .build();
        logger.info("orderLine: {}", orderLineList);

        UUID createdOrderId = orderRepository.save(newOrder).getId();

        orderLineList.flatMap(orderLineRequest -> {
            // create each OrderLine asynchronously
            return Mono.fromRunnable(() -> {
                orderLineService.create(createdOrderId, orderLineRequest);
            }).subscribeOn(Schedulers.boundedElastic());
        }).subscribe();

    }

    @Override
    public void delete(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        // delete order_lines of order
        List<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(orderId);
        orderLineList.stream().map(OrderLine::getId).forEach(orderLineService::delete);

        // delete order
        orderRepository.delete(order);
    }

    @Override
    public Order getById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );
    }

}

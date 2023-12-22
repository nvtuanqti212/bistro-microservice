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
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.bistrocheese.orderservice.constant.RabbitConstant.ROUTING_QUEUE;


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

        List<OrderLineRequest> orderLineList = req.getOrderLines();

        Order newOrder = Order.builder()
                .staffId(staffId)
                .status(OrderStatus.PENDING)
                .totalPrice(BigDecimal.valueOf(0))
                .createdAt(Timestamp.from(new Date().toInstant()))
                .build();
        logger.info("orderLine: {}", orderLineList);

        UUID createdOrderId = orderRepository.save(newOrder).getId();

        orderLineList.forEach(orderLine -> {
            orderLineService.create(createdOrderId, orderLine);
        });

    }

    @Override
    @RabbitListener(queues = {ROUTING_QUEUE})
    public void completeOrder(String message) {

        UUID orderId = UUID.fromString(message);
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        logger.info("At orderServiceIml, order completed: {}", order);

        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
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

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findAllByStaffId(userId);
    }
}

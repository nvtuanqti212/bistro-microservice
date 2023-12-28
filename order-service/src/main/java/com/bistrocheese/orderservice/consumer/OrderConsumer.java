package com.bistrocheese.orderservice.consumer;

import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.Order;
import com.bistrocheese.orderservice.model.OrderStatus;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.RedisHashService;
import com.bistrocheese.orderservice.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.bistrocheese.orderservice.constant.RabbitConstant.ROUTING_QUEUE;
import static com.bistrocheese.orderservice.constant.RedisConstant.ORDERS;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;
    private  final RedisHashService redisHashService;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @RabbitListener(queues = {ROUTING_QUEUE})
    public void completeOrder(String message) {

        UUID orderId = UUID.fromString(message);
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        logger.info("At orderServiceIml, order completed: {}", order);


        order.setStatus(OrderStatus.COMPLETED);
        redisHashService.hSet(ORDERS, message, order);
        orderRepository.save(order);
    }
}

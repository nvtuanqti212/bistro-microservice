package com.bistrocheese.paymentservice.publisher;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import static com.bistrocheese.paymentservice.constant.RabbitConstant.*;

import java.util.UUID;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class BillPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final static Logger logger = LogManager.getLogger(BillPublisher.class);

    public void completeOrder(UUID orderId){
        logger.info("Order id complete {}", orderId.toString());
        rabbitTemplate.convertAndSend("", ROUTING_KEY, orderId.toString());
    }

}

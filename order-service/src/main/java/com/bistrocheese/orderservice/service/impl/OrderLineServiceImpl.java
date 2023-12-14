package com.bistrocheese.orderservice.service.impl;


import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.OrderLineRequest;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.Order;
import com.bistrocheese.orderservice.model.OrderLine;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineRepository repository;
    private final OrderRepository orderRepository;


    @Override
    public void create(UUID orderId, OrderLineRequest req) {
        OrderLine newOrderLine = new OrderLine();

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        //TODO: check if food is available in inventory
        newOrderLine.setOrder(order);
        newOrderLine.setFoodId(req.getFoodId());
        newOrderLine.setQuantity(req.getQuantity());
        newOrderLine.calculatePrice(req.getPrice());

        repository.save(newOrderLine);
    }

    @Override
    public void update(UUID orderLineId, OrderLineRequest req) {
        OrderLine orderLine = getOrderLineById(orderLineId);
        orderLine.setQuantity(req.getQuantity());
        //TODO: import or export inventory
        repository.save(orderLine);
    }

    @Override
    public void delete(UUID orderLineId) {
        OrderLine orderLine = getOrderLineById(orderLineId);
        //TODO: import back to inventory
        repository.delete(orderLine);
    }

    @Override
    public OrderLine getById(UUID orderLineId) {
        return getOrderLineById(orderLineId);
    }

    private OrderLine getOrderLineById(UUID orderLineId) {
        return repository.findById(orderLineId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_LINE_NOT_FOUND)
        );
    }
}

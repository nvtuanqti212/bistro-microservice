package com.bistrocheese.orderservice.service.impl;

import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.OrderCreateRequest;
import com.bistrocheese.orderservice.dto.response.OrderCreateResponse;
import com.bistrocheese.orderservice.dto.response.OrderDeleteResponse;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.*;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.repository.OrderTableRepository;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderTableRepository orderTableRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public OrderCreateResponse createOrder(OrderCreateRequest req) {
        OrderTable orderTable = orderTableRepository.findById(req.getTableId()).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_TABLE_NOT_FOUND)
        );

        if (!orderTable.getTableStatus().equals(TableStatus.EMPTY)) {
            throw new CustomException(APIStatus.ORDER_TABLE_NOT_EMPTY);
        }

        orderTableRepository.updateTableStatusById(TableStatus.OCCUPIED, orderTable.getId());
        var newOrder = Order.builder()
                .staffId(req.getStaffId())
                .orderTable(orderTable)
                .orderDate(new Date())
                .status(OrderStatus.PENDING)
                .build();
        orderRepository.save(newOrder);

        return OrderCreateResponse.of(newOrder.getId());
    }

    @Override
    public OrderDeleteResponse deleteOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );
        List<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(id);

        for (OrderLine orderLine : orderLineList) {
            orderLineRepository.deleteById(orderLine.getId());
        }
        orderRepository.deleteById(id);
        orderTableRepository.updateTableStatusById(TableStatus.EMPTY, order.getOrderTable().getId());
        return OrderDeleteResponse.of(id);
    }
}

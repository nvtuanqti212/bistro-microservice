package com.bistrocheese.orderservice.service.impl;

import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.order.OrderLineRequest;
import com.bistrocheese.orderservice.dto.request.order.OrderRequest;
import com.bistrocheese.orderservice.dto.response.order.OrderLineResponse;
import com.bistrocheese.orderservice.dto.response.order.OrderResponse;
import com.bistrocheese.orderservice.dto.response.table.TableInfoResponse;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.*;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.repository.OrderTableRepository;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderTableRepository orderTableRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;


    @Override
    public List<OrderResponse> getOrders(UUID staffId) {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<OrderTable> orderTableList = orderTableRepository.findAll();
        getAllOrders(orderResponseList, orderTableList, staffId);
        return orderResponseList;
    }

    @Override
    public void createOrder(OrderRequest orderRequest, UUID staffId){
        OrderTable orderTable = orderTableRepository.findById(orderRequest.getTableId()).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_TABLE_NOT_FOUND)
        );
        if (!orderTable.getTableStatus().equals(TableStatus.EMPTY)) {
            throw new CustomException(APIStatus.ORDER_TABLE_NOT_EMPTY);
        }
        orderTableRepository.updateTableStatusById(TableStatus.OCCUPIED, orderTable.getId());
        var newOrder = Order.builder()
                .staffId(staffId)
                .orderTable(orderTable)
                .orderDate(new Date())
                .status(OrderStatus.PENDING)
                .build();
        orderRepository.save(newOrder);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );
        Stream<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(orderId);
        //TODO: add food quantity back to inventory

//        orderLineList.map(OrderLine::getId).forEach(orderLineRepository::deleteById);

        orderRepository.deleteById(orderId);
        orderTableRepository.updateTableStatusById(TableStatus.EMPTY, order.getOrderTable().getId());
    }

    @Override
    public void createOrderLine(UUID orderId, OrderLineRequest orderLineRequest) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
        );

        //TODO: add food to Order line
        //TODO: update food quantity in inventory
    }

    @Override
    public void updateOrderLine(UUID orderLineId, OrderLineRequest orderLineRequest) {
        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_LINE_NOT_FOUND)
        );
        //TODO: update food quantity back to inventory
        orderLineRepository.updateQuantityById(orderLineRequest.getQuantity(), orderLineId);
    }

    @Override
    public void deleteOrderLine(UUID orderLineId) {
        OrderLine orderLine = orderLineRepository.findById(orderLineId).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_LINE_NOT_FOUND)
        );
        //TODO: add food quantity back to inventory
        orderLineRepository.deleteById(orderLineId);
    }

    private void getAllOrders( List<OrderResponse> orderResponseList, List<OrderTable> orderTableList, UUID staffId) {
        for (OrderTable orderTable : orderTableList) {
            if (orderTable.getTableStatus().equals(TableStatus.EMPTY)) {
                var orderResponse = OrderResponse.builder()
                        .table(tableInfoResponseBuild(orderTable))
                        .build();
                orderResponseList.add(orderResponse);
                continue;
            }
            List<OrderLineResponse> orderLineResponseList = new ArrayList<>();
            Order order = orderRepository.findOrder(staffId, orderTable.getId(), OrderStatus.PENDING);
            Stream<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(order.getId());
            orderLineList.map(this::orderLineResponseBuild).forEach(orderLineResponseList::add);
//            for (OrderLine orderLine : orderLineList) {
//                orderLineResponseList.add(orderLineResponseBuild(orderLine));
//            }
            var orderResponse =  OrderResponse.builder()
                    .table(tableInfoResponseBuild(orderTable))
                    .orderId(order.getId())
                    .orderLines(orderLineResponseList)
                    .build();
            orderResponseList.add(orderResponse);
        }
    }

    private TableInfoResponse tableInfoResponseBuild (OrderTable orderTable) {
        return TableInfoResponse.builder()
                .id(orderTable.getId())
                .number(orderTable.getTableNumber())
                .status(orderTable.getTableStatus().ordinal())
                .build();
    }

    private OrderLineResponse orderLineResponseBuild(OrderLine orderLine) {
        //TODO: add name and price to orderLineResponseList
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .foodId(orderLine.getFoodId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}

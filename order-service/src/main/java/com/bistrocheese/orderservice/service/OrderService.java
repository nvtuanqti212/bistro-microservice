package com.bistrocheese.orderservice.service;


import com.bistrocheese.orderservice.dto.request.order.OrderLineRequest;
import com.bistrocheese.orderservice.dto.request.order.OrderRequest;
import com.bistrocheese.orderservice.dto.response.order.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
//    List<OrderResponse> getOrders();
    void createOrder(OrderRequest orderRequest, String staffId);
//    void deleteOrder(UUID orderId);
//    void createOrderLine(UUID orderId, OrderLineRequest orderLineRequest);
//    void updateOrderLine(UUID orderLineId, OrderLineRequest orderLineRequest);
//    void deleteOrderLine(UUID orderLineId);
}

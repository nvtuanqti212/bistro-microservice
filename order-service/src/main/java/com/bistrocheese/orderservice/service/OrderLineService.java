package com.bistrocheese.orderservice.service;

import com.bistrocheese.orderservice.dto.request.OrderLineRequest;
import com.bistrocheese.orderservice.model.OrderLine;

import java.util.UUID;

public interface OrderLineService {
    void create(UUID orderId, OrderLineRequest req);
    void update(UUID orderLineId, OrderLineRequest req);
    void delete(UUID orderLineId);
    OrderLine getById(UUID orderLineId);
//    BigDecimal calculateSubTotal(UUID orderLineId);
}

package com.bistrocheese.orderservice.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID id;
    private UserResponse user;
    private List<OrderLineResponse> orderLines;
    private BigDecimal subTotal;
    private String status;
}

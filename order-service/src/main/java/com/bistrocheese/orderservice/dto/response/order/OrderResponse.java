package com.bistrocheese.orderservice.dto.response.order;

import com.bistrocheese.orderservice.dto.response.table.TableInfoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private TableInfoResponse table;
    @JsonProperty("order_id")
    private UUID orderId;
    @JsonProperty("order_lines")
    private List<OrderLineResponse> orderLines;
}

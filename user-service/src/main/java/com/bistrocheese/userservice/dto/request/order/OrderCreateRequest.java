package com.bistrocheese.userservice.dto.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderCreateRequest {
    @JsonProperty("staffId")
    private String staffId;
    @JsonProperty("order_lines")
    private List<OrderLineRequest> orderLines;
}

package com.bistrocheese.orderservice.dto.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {
    private UUID id;
    private String name;
    private String image;
    private BigDecimal price;
    private Integer quantity;
}

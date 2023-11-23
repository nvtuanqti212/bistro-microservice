package com.bistrocheese.orderservice.dto.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {
    private UUID id;
    @JsonProperty("food_id")
    private UUID foodId;
//    @JsonProperty("food_name")
//    private String foodName;
//    @JsonProperty("food_price")
//    private Long foodPrice;
    private Integer quantity;
}

package com.bistrocheese.orderservice.dto.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {
    @JsonProperty("food_id")
    private UUID foodId;
    private Integer quantity;
}

package com.bistrocheese.userservice.dto.request.order;

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
    @JsonProperty("quantity")
    private Integer quantity;
}
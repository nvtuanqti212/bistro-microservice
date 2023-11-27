package com.bistrocheese.orderservice.dto.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @JsonProperty("table_id")
    private Long tableId;
}

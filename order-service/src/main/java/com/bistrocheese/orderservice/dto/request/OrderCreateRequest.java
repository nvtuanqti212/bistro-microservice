package com.bistrocheese.orderservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrderCreateRequest {
    @JsonProperty("tableId")
    private Long tableId;

    @JsonProperty("staffId")
    private UUID staffId;
}

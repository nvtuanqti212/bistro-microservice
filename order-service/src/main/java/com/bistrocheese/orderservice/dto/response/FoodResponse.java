package com.bistrocheese.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FoodResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("foodImage")
    private String foodImage;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("status")
    private Integer status;
}

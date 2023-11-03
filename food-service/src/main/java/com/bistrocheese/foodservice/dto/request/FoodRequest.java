package com.bistrocheese.foodservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class FoodRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Integer category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("product_image")
    private String productImage;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("status")
    private Integer status;
}

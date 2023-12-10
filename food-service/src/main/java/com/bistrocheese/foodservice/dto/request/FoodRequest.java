package com.bistrocheese.foodservice.dto.request;

import com.bistrocheese.foodservice.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @JsonProperty("image")
    private String image;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("status")
    private Integer status;
}

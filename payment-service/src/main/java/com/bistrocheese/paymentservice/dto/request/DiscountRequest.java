package com.bistrocheese.paymentservice.dto.request;

import com.bistrocheese.paymentservice.model.DiscountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequest {
    private String name;
    @JsonProperty("discount_type")
    private DiscountType discountType;
    private BigDecimal value;
    @JsonProperty("uses_max")
    private Integer usesMax;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("is_active")
    private Boolean isActive;
}

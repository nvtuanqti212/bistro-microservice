package com.bistrocheese.paymentservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OrderResponse {
    private UUID id;

    private String staffId;

    private String phoneNumber;

    private BigDecimal totalPrice;

    @JsonFormat(timezone = "GMT+7")
    private Timestamp createdAt;
}

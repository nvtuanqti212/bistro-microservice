package com.bistrocheese.userservice.dto.request.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {
    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("discount_id")
    private Integer discountId;

    @JsonProperty("paid")
    private BigDecimal paid;

    // Payment
    @JsonProperty("method_id")
    private Integer methodId;

    @JsonProperty("payment_type")
    private PaymentType paymentType;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;
}

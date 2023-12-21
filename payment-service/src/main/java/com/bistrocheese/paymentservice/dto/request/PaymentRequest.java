package com.bistrocheese.paymentservice.dto.request;

import com.bistrocheese.paymentservice.model.PaymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @JsonProperty("method_id")
    private Integer methodId;

    @JsonProperty("payment_type")
    private PaymentType paymentType;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
